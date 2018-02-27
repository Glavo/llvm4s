package org.glavo.llvm.ir

import org.glavo.llvm._
import org.glavo.llvm.internal.{Handle, JNIUtils, Unsigned}
import org.glavo.llvm.internal.ir.TypeImpl

import scala.collection.mutable

/** The instances of the Type class are immutable: once they are created,
  * they are never changed.  Also note that only one instance of a particular
  * type is ever created.  Thus seeing if two types are equal is a matter of
  * doing a trivial pointer comparison. To enforce that no two equal instances
  * are created, Type instances can only be created via static factory methods
  * in class Type and in derived classes.  Once allocated, Types are never
  * free'd.
  */
abstract class Type private[llvm](private[llvm] val handle: Long) {
  private def checkHandle(): Unit = if (handle == 0) throw new NullPointerException

  val id: Type.ID = Type.ID(TypeImpl.getTypeId(handle))

  val context: Context = {
    checkHandle()
    TypeImpl.getContext(handle) match {
      case 0 => null
      case h if h == Context.Global.handle => Context.Global
      case h => new Context(h)
    }
  }

  def isVoidType: Boolean = id == Type.ID.Void

  def isHalfType: Boolean = id == Type.ID.Half

  def isFloatType: Boolean = id == Type.ID.Float

  def isDoubleType: Boolean = id == Type.ID.Double

  def isX86_FP80Type: Boolean = id == Type.ID.X86_FP80

  def isFP128Type: Boolean = id == Type.ID.FP128

  def isPPC_FP128Type: Boolean = id == Type.ID.PPC_FP128

  def isFloatingPointType: Boolean = isHalfType || isFloatType || isDoubleType || isX86_FP80Type ||
    isFP128Type || isPPC_FP128Type

  def isX86_MMXType: Boolean = id == Type.ID.X86_MMX

  def isFPOrFPVectorType: Boolean = TypeImpl.isFPOrFPVectorTy(handle)

  def isLabelType: Boolean = id == Type.ID.Label

  def isMetadataType: Boolean = id == Type.ID.Metadata

  def isTokenType: Boolean = id == Type.ID.Token

  def isIntegerType: Boolean = id == Type.ID.Integer

  def isIntegerType(bitWidth: Int@Unsigned): Boolean = TypeImpl.isIntegerTy(handle, bitWidth)

  def isIntOrIntVectorType: Boolean = TypeImpl.isIntOrIntVectorTy(handle)

  def isIntOrIntVectorType(bitWidth: Int@Unsigned): Boolean = TypeImpl.isIntOrIntVectorTy(handle, bitWidth)

  def isFunctionType: Boolean = id == Type.ID.Function

  def isStructType: Boolean = id == Type.ID.Struct

  def isArrayType: Boolean = id == Type.ID.Array

  def isPointerType: Boolean = id == Type.ID.Pointer

  def isPtrOrPtrVectorType: Boolean = TypeImpl.isPtrOrPtrVectorTy(handle)

  def isVectorType: Boolean = id == Type.ID.Vector

  def canLosslesslyBitCastTo(typ: Type): Boolean = TypeImpl.canLosslesslyBitCastTo(handle, typ.handle)

  def isEmptyType: Boolean = TypeImpl.isEmptyTy(handle)

  def isFirstClassType: Boolean = id != Type.ID.Function && id != Type.ID.Void

  def isSingleValueType: Boolean = isFloatingPointType || isX86_MMXType || isIntegerType || isPointerType || isVectorType

  def isAggregateType: Boolean = id == Type.ID.Struct || id == Type.ID.Array

  def getPrimitiveSizeInBits: Int@Unsigned = TypeImpl.getPrimitiveSizeInBits(handle)

  def getScalarSizeInBits: Int@Unsigned = TypeImpl.getScalarSizeInBits(handle)

  def getFPMantissaWidth: Int = TypeImpl.getFPMantissaWidth(handle)

  def getScalarType: Type = if (isVectorType) ??? else this //todo

  def * : PointerType = PointerType(this)

  override def hashCode(): Int = handle.toInt

  override def equals(obj: scala.Any): Boolean = obj match {
    case t: Type => this.handle == t.handle
    case _ => false
  }

  override lazy val toString: String = s"Type(${JNIUtils.bytes2str(TypeImpl.toString(handle))})"
}

object Type {

  /** Definitions of all of the base types for the Type system. */
  abstract sealed class ID(val id: Int, val floatingPoint: Boolean = false)

  object ID {

    def apply(id: Int@Unsigned): ID =
      if (id >= 0 && id < 17)
        values(id)
      else
        throw UnknownTypeIdException(s"id=${java.lang.Integer.toUnsignedLong(id)}")

    def values: scala.Array[ID] = scala.Array(Void, Half, Float, Double, X86_FP80, FP128, PPC_FP128, Label, Metadata,
      X86_MMX, Token, Integer, Function, Struct, Array, Pointer, Vector)

    /** type with no size */
    case object Void extends ID(0)

    /** 16-bit floating point type */
    case object Half extends ID(1, true)

    /** 32-bit floating point type */
    case object Float extends ID(2, true)

    /** 64-bit floating point type */
    case object Double extends ID(3, true)

    /** 80-bit floating point type (X87) */
    case object X86_FP80 extends ID(4, true)

    /** 128-bit floating point type (112-bit mantissa) */
    case object FP128 extends ID(5, true)

    /** 128-bit floating point type (two 64-bits, PowerPC) */
    case object PPC_FP128 extends ID(6, true)

    /** Labels */
    case object Label extends ID(7)

    /** Metadata */
    case object Metadata extends ID(8)

    /** MMX vectors (64 bits, X86 specific) */
    case object X86_MMX extends ID(9)

    /** Tokens */
    case object Token extends ID(10)

    /** Arbitrary bit width integers */
    case object Integer extends ID(11)

    /** Functions */
    case object Function extends ID(12)

    /** Structures */
    case object Struct extends ID(13)

    /** Arrays */
    case object Array extends ID(14)

    /** Pointers */
    case object Pointer extends ID(15)

    /** SIMD 'packed' format, or other vector type */
    case object Vector extends ID(16)

  }

  private[llvm] val typeList: mutable.Set[Type] = mutable.HashSet()

  private[llvm] def apply(handle: Long@Handle(classOf[Type])): Type = {
    if (handle == 0)
      null
    else
      typeList.find(_.handle == handle).getOrElse({
        val t = Type.ID(TypeImpl.getTypeId(handle)) match {
          case ID.Void =>
            new VoidType(handle)
          case ID.Label =>
            new LabelType(handle)
          case ID.Metadata =>
            new MetadataType(handle)
          case ID.X86_MMX =>
            new X86_MMXType(handle)
          case ID.Integer =>
            new IntegerType(handle)
          case id if id.floatingPoint =>
            new FloatingPointType(handle)
          case ID.Function =>
            new FunctionType(handle)
          case ID.Pointer =>
            new PointerType(handle)
          //todo
        }
        typeList += t
        t
      })
  }
}

