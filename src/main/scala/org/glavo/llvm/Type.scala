package org.glavo.llvm

import java.util.Objects

import org.bytedeco.javacpp.{LLVM, PointerPointer}

import scala.annotation.switch

abstract case class Type(delegate: LLVM.LLVMTypeRef) {

  Objects.requireNonNull(delegate)

  lazy val kind: Type.Kind = Type.Kind(LLVM.LLVMGetTypeKind(delegate))

  lazy val context: Context =
    new Context(Objects.requireNonNull(LLVM.LLVMGetTypeContext(delegate)))

  def isSized: Boolean = if (LLVM.LLVMTypeIsSized(delegate) != 0) true else false

  def apply(paramTypes: Type*): Type.Function = Type.function(this, paramTypes)

  override def toString: String = {
    val bs = LLVM.LLVMPrintTypeToString(delegate)
    val s = bs.getString("UTF-8")
    LLVM.LLVMDisposeMessage(bs)
    s"Type($s)"
  }
}

object Type {

  sealed abstract class Kind(val id: Int)

  object Kind {

    def apply(id: Int): Kind = (id: @switch) match {
      case LLVM.LLVMVoidTypeKind => Void
      case LLVM.LLVMHalfTypeKind => Half
      case LLVM.LLVMFloatTypeKind => Float
      case LLVM.LLVMDoubleTypeKind => Double
      case LLVM.LLVMX86_FP80TypeKind => X86_FP80
      case LLVM.LLVMFP128TypeKind => FP128
      case LLVM.LLVMPPC_FP128TypeKind => PPC_FP128
      case LLVM.LLVMLabelTypeKind => Label
      case LLVM.LLVMIntegerTypeKind => Integer
      case LLVM.LLVMFunctionTypeKind => Function
      case LLVM.LLVMStructTypeKind => Struct
      case LLVM.LLVMArrayTypeKind => Array
      case LLVM.LLVMPointerTypeKind => Pointer
      case LLVM.LLVMVectorTypeKind => Vector
      case LLVM.LLVMMetadataTypeKind => Metadata
      case LLVM.LLVMX86_MMXTypeKind => X86_FP80
      case LLVM.LLVMTokenTypeKind => Token
      case _ => Unknown(id)
    }

    /** type with no size */
    case object Void extends Kind(LLVM.LLVMVoidTypeKind)

    /** 16 bit floating point type */
    case object Half extends Kind(LLVM.LLVMHalfTypeKind)

    /** 32 bit floating point type */
    case object Float extends Kind(LLVM.LLVMFloatTypeKind)


    /** 64 bit floating point type */
    case object Double extends Kind(LLVM.LLVMDoubleTypeKind)


    /** 80 bit floating point type (X87) */
    case object X86_FP80 extends Kind(LLVM.LLVMX86_FP80TypeKind)


    /** 128 bit floating point type (112-bit mantissa) */
    case object FP128 extends Kind(LLVM.LLVMFP128TypeKind)


    /** 128 bit floating point type (two 64-bits) */
    case object PPC_FP128 extends Kind(LLVM.LLVMPPC_FP128TypeKind)


    /** Labels */
    case object Label extends Kind(LLVM.LLVMLabelTypeKind)


    /** Arbitrary bit width integers */
    case object Integer extends Kind(LLVM.LLVMIntegerTypeKind)


    /** Functions */
    case object Function extends Kind(LLVM.LLVMFunctionTypeKind)

    /** Structures */
    case object Struct extends Kind(LLVM.LLVMStructTypeKind)


    /** Arrays */
    case object Array extends Kind(LLVM.LLVMArrayTypeKind)


    /** Pointers */
    case object Pointer extends Kind(LLVM.LLVMPointerTypeKind)


    /** SIMD 'packed' format, or other vector type */
    case object Vector extends Kind(LLVM.LLVMVectorTypeKind)


    /** Metadata */
    case object Metadata extends Kind(LLVM.LLVMMetadataTypeKind)


    /** X86 MMX */
    case object MX86_MMX extends Kind(LLVM.LLVMX86_MMXTypeKind)


    /** Tokens */
    case object Token extends Kind(LLVM.LLVMTokenTypeKind)


    case class Unknown(override val id: Int) extends Kind(id)

  }

  @inline
  def apply(typeRef: LLVM.LLVMTypeRef): Type = {
    Objects.requireNonNull(typeRef)
    val k = Kind(LLVM.LLVMGetTypeKind(typeRef))
    k match {
      case Kind.Integer => Integer(typeRef)
      case Kind.Function => new Function(typeRef)
      //todo
      case _ => Unknown(typeRef)
    }
  }

  sealed class Integer(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate) {
    lazy val width: Int = LLVM.LLVMGetIntTypeWidth(delegate)
  }

  object Integer {
    def apply(intTypeRef: LLVM.LLVMTypeRef): Integer = {
      val w = LLVM.LLVMGetIntTypeWidth(intTypeRef)
      val context = Context(LLVM.LLVMGetTypeContext(intTypeRef))
      apply(w)(context)
    }

    def apply(numBits: Int)(implicit context: Context): Integer = (numBits: @switch) match {
      case 1 => int1
      case 8 => int8
      case 16 => int16
      case 32 => int32
      case 64 => int64
      case 128 => int128
      case _ => new Integer(LLVM.LLVMIntTypeInContext(context.delegate, numBits))
    }
  }

  class Int1(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int1 {
    def apply(int: LLVM.LLVMTypeRef): Int1 = new Int1(int)

    def apply()(implicit context: Context): Int1 =
      int1(context)
  }

  class Int8(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int8 {
    def apply(int: LLVM.LLVMTypeRef): Int8 = new Int8(int)

    def apply()(implicit context: Context): Int8 =
      int8(context)
  }

  class Int16(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int16 {
    def apply(int: LLVM.LLVMTypeRef): Int16 = new Int16(int)

    def apply()(implicit context: Context): Int16 =
      int16(context)
  }

  class Int32(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int32 {
    def apply(int: LLVM.LLVMTypeRef): Int32 = new Int32(int)

    def apply()(implicit context: Context): Int32 =
      int32(context)
  }

  class Int64(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int64 {
    def apply(int: LLVM.LLVMTypeRef): Int64 = new Int64(int)

    def apply()(implicit context: Context): Int64 =
      int64(context)
  }

  class Int128(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int128 {
    def apply(int: LLVM.LLVMTypeRef): Int128 = new Int128(int)

    def apply()(implicit context: Context): Int128 =
      int128(context)
  }

  @inline
  def int1(implicit context: Context): Int1 = Int1(LLVM.LLVMInt1TypeInContext(context.delegate))

  @inline
  def int8(implicit context: Context): Int8 = Int8(LLVM.LLVMInt8TypeInContext(context.delegate))

  @inline
  def int16(implicit context: Context): Int16 = Int16(LLVM.LLVMInt16TypeInContext(context.delegate))

  @inline
  def int32(implicit context: Context): Int32 = Int32(LLVM.LLVMInt32TypeInContext(context.delegate))

  @inline
  def int64(implicit context: Context): Int64 = Int64(LLVM.LLVMInt64TypeInContext(context.delegate))

  @inline
  def int128(implicit context: Context): Int128 = Int128(LLVM.LLVMInt128TypeInContext(context.delegate))

  class Function private[llvm](override val delegate: LLVM.LLVMTypeRef, private var _pts: Seq[Type]) extends Type(delegate) {
    def this(delegate: LLVM.LLVMTypeRef) {
      this(delegate, null)
    }

    def paramTypes: Seq[Type] = {
      if (_pts != null)
        _pts
      else {
        val i = LLVM.LLVMCountParamTypes(delegate)
        if (i != 0) {
          val pp = new PointerPointer[LLVM.LLVMTypeRef](i)
          LLVM.LLVMGetParamTypes(delegate, pp)
          _pts = (0 until i).map(index => Type(pp.get(classOf[LLVM.LLVMTypeRef], index)))
          _pts
        } else {
          _pts = Seq()
          _pts
        }
      }
    }
  }

  object Function {
    def apply(funTypeRef: LLVM.LLVMTypeRef): Function = {
      val r = new Function(funTypeRef)
      if (r.kind == Kind.Function)
        r
      else throw new IllegalArgumentException(s"$r isn't function type ref")
    }

    def apply(returnType: Type, paramTypes: Seq[Type], isVarargs: Boolean = false): Function =
      function(returnType, paramTypes, isVarargs)
  }

  def function(returnType: Type, paramTypes: Seq[Type], isVarargs: Boolean = false): Function = {
    Objects.requireNonNull(returnType)
    Objects.requireNonNull(paramTypes)
    val ps = paramTypes.view.map(_.delegate).toArray

    if (ps.length != 0)
      new Function(
        LLVM.LLVMFunctionType(returnType.delegate, ps(0), ps.length, if (isVarargs) 1 else 0),
        paramTypes
      )
    else
      new Function(
        LLVM.LLVMFunctionType(returnType.delegate, new Array[LLVM.LLVMTypeRef](1)(0), 0, if (isVarargs) 1 else 0),
        paramTypes)
  }

  class Unknown(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Unknown {
    def apply(typeRef: LLVM.LLVMTypeRef): Unknown = new Unknown(typeRef)
  }

}
