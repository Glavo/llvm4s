package org.glavo.llvm.core

import java.util.Objects

import org.bytedeco.javacpp.PointerPointer
import org.bytedeco.javacpp.LLVM._

import scala.annotation.switch

abstract case class Type(delegate: LLVMTypeRef, var _context: Context) {
  def this(delegate: LLVMTypeRef) {
    this(delegate, null)
  }

  Objects.requireNonNull(delegate)

  lazy val kind: Type.Kind = Type.Kind(LLVMGetTypeKind(delegate))

  lazy val context: Context = {
    if (_context != null)
      _context
    else
      new Context(Objects.requireNonNull(LLVMGetTypeContext(delegate)))
  }

  lazy val isSized: Boolean = LLVMTypeIsSized(delegate) != 0

  def apply(paramTypes: Type*): Type.Function = Type.function(this, paramTypes)

  def apply(elementCount: Int): Type.Array = Type.Array(this, elementCount)

  def * : Type.Pointer = Type.Pointer(this)

  def *(addressSpace: Int): Type.Pointer = Type.Pointer(this, addressSpace)

  override def toString: String = {
    val bs = LLVMPrintTypeToString(delegate)
    val s = bs.getString(org.glavo.llvm.LLVMEncoding)
    LLVMDisposeMessage(bs)
    s"Type($s)"
  }
}

object Type {

  sealed abstract class Kind(val id: Int)

  object Kind {

    def apply(id: Int): Kind = (id: @switch) match {
      case LLVMVoidTypeKind => Void
      case LLVMHalfTypeKind => Half
      case LLVMFloatTypeKind => Float
      case LLVMDoubleTypeKind => Double
      case LLVMX86_FP80TypeKind => X86_FP80
      case LLVMFP128TypeKind => FP128
      case LLVMPPC_FP128TypeKind => PPC_FP128
      case LLVMLabelTypeKind => Label
      case LLVMIntegerTypeKind => Integer
      case LLVMFunctionTypeKind => Function
      case LLVMStructTypeKind => Struct
      case LLVMArrayTypeKind => Array
      case LLVMPointerTypeKind => Pointer
      case LLVMVectorTypeKind => Vector
      case LLVMMetadataTypeKind => Metadata
      case LLVMX86_MMXTypeKind => X86_FP80
      case LLVMTokenTypeKind => Token
      case _ => Unknown(id)
    }

    /** type with no size */
    case object Void extends Kind(LLVMVoidTypeKind)

    /** 16 bit floating point type */
    case object Half extends Kind(LLVMHalfTypeKind)

    /** 32 bit floating point type */
    case object Float extends Kind(LLVMFloatTypeKind)


    /** 64 bit floating point type */
    case object Double extends Kind(LLVMDoubleTypeKind)


    /** 80 bit floating point type (X87) */
    case object X86_FP80 extends Kind(LLVMX86_FP80TypeKind)


    /** 128 bit floating point type (112-bit mantissa) */
    case object FP128 extends Kind(LLVMFP128TypeKind)


    /** 128 bit floating point type (two 64-bits) */
    case object PPC_FP128 extends Kind(LLVMPPC_FP128TypeKind)


    /** Labels */
    case object Label extends Kind(LLVMLabelTypeKind)


    /** Arbitrary bit width integers */
    case object Integer extends Kind(LLVMIntegerTypeKind)


    /** Functions */
    case object Function extends Kind(LLVMFunctionTypeKind)

    /** Structures */
    case object Struct extends Kind(LLVMStructTypeKind)


    /** Arrays */
    case object Array extends Kind(LLVMArrayTypeKind)


    /** Pointers */
    case object Pointer extends Kind(LLVMPointerTypeKind)


    /** SIMD 'packed' format, or other vector type */
    case object Vector extends Kind(LLVMVectorTypeKind)


    /** Metadata */
    case object Metadata extends Kind(LLVMMetadataTypeKind)


    /** X86 MMX */
    case object MX86_MMX extends Kind(LLVMX86_MMXTypeKind)


    /** Tokens */
    case object Token extends Kind(LLVMTokenTypeKind)


    case class Unknown(override val id: Int) extends Kind(id)

  }

  @inline
  def apply(typeRef: LLVMTypeRef): Type = {
    Objects.requireNonNull(typeRef)
    val k = Kind(LLVMGetTypeKind(typeRef))
    k match {
      case Kind.Integer => Integer(typeRef)
      case Kind.Half => new Half(typeRef)
      case Kind.Float => new Float(typeRef)
      case Kind.Double => new Double(typeRef)
      case Kind.X86_FP80 => new X86FP80(typeRef)
      case Kind.FP128 => new FP128(typeRef)
      case Kind.PPC_FP128 => new PPCFP128(typeRef)
      case Kind.Function => new Function(typeRef)
      case Kind.Struct => new Struct(typeRef)
      case Kind.Array => new Array(typeRef)
      case Kind.Pointer => new Pointer(typeRef)
      case Kind.Vector => new Vector(typeRef)
      case Kind.Label => new Label(typeRef)
      case Kind.Void => new Label(typeRef)
      //todo
      case _ => Unknown(typeRef)
    }
  }

  // Integer Types

  sealed class Integer(override val delegate: LLVMTypeRef) extends Type(delegate) {
    lazy val width: Int = LLVMGetIntTypeWidth(delegate)
  }

  object Integer {
    def apply(intTypeRef: LLVMTypeRef): Integer = {
      Objects.requireNonNull(intTypeRef)
      val w = LLVMGetIntTypeWidth(intTypeRef)
      val context = Context(LLVMGetTypeContext(intTypeRef))
      apply(w)(context)
    }

    def apply(numBits: Int)(implicit context: Context): Integer = (numBits: @switch) match {
      case 1 => int1
      case 8 => int8
      case 16 => int16
      case 32 => int32
      case 64 => int64
      case 128 => int128
      case _ => new Integer(LLVMIntTypeInContext(context.delegate, numBits))
    }
  }

  class Int1(override val delegate: LLVMTypeRef) extends Integer(delegate)

  object Int1 {
    def apply(int: LLVMTypeRef): Int1 = {
      Objects.requireNonNull(int)
      if (Kind(LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int1(int)
    }

    def apply()(implicit context: Context): Int1 =
      int1(context)
  }

  class Int8(override val delegate: LLVMTypeRef) extends Integer(delegate)

  object Int8 {
    def apply(int: LLVMTypeRef): Int8 = {
      Objects.requireNonNull(int)
      if (Kind(LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int8(int)
    }

    def apply()(implicit context: Context): Int8 =
      int8(context)
  }

  class Int16(override val delegate: LLVMTypeRef) extends Integer(delegate)

  object Int16 {
    def apply(int: LLVMTypeRef): Int16 = {
      Objects.requireNonNull(int)
      if (Kind(LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int16(int)
    }

    def apply()(implicit context: Context): Int16 =
      int16(context)
  }

  class Int32(override val delegate: LLVMTypeRef) extends Integer(delegate)

  object Int32 {
    def apply(int: LLVMTypeRef): Int32 = {
      Objects.requireNonNull(int)
      if (Kind(LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int32(int)
    }

    def apply()(implicit context: Context): Int32 =
      int32(context)
  }

  class Int64(override val delegate: LLVMTypeRef) extends Integer(delegate)

  object Int64 {
    def apply(int: LLVMTypeRef): Int64 = {
      Objects.requireNonNull(int)
      if (Kind(LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int64(int)
    }

    def apply()(implicit context: Context): Int64 =
      int64(context)
  }

  class Int128(override val delegate: LLVMTypeRef) extends Integer(delegate)

  object Int128 {
    def apply(int: LLVMTypeRef): Int128 = {
      Objects.requireNonNull(int)
      if (Kind(LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int128(int)
    }

    def apply()(implicit context: Context): Int128 =
      int128(context)
  }

  @inline
  def integer(numBits: Int)(implicit context: Context): Integer = Integer(numBits)(context)

  @inline
  def int1(implicit context: Context): Int1 = new Int1(LLVMInt1TypeInContext(context.delegate))

  @inline
  def int8(implicit context: Context): Int8 = new Int8(LLVMInt8TypeInContext(context.delegate))

  @inline
  def int16(implicit context: Context): Int16 = new Int16(LLVMInt16TypeInContext(context.delegate))

  @inline
  def int32(implicit context: Context): Int32 = new Int32(LLVMInt32TypeInContext(context.delegate))

  @inline
  def int64(implicit context: Context): Int64 = new Int64(LLVMInt64TypeInContext(context.delegate))

  @inline
  def int128(implicit context: Context): Int128 = new Int128(LLVMInt128TypeInContext(context.delegate))

  // Floating Types

  sealed class Floating(override val delegate: LLVMTypeRef) extends Type(delegate)

  object Floating {
    def apply(floating: LLVMTypeRef): Floating = {
      Objects.requireNonNull(floating)
      Kind(LLVMGetTypeKind(floating)) match {
        case Kind.Half => new Half(floating)
        case Kind.Float => new Float(floating)
        case Kind.Double => new Double(floating)
        case Kind.X86_FP80 => new X86FP80(floating)
        case Kind.FP128 => new FP128(floating)
        case Kind.PPC_FP128 => new PPCFP128(floating)
        case _ => throw new IllegalArgumentException
      }
    }
  }

  class Half(override val delegate: LLVMTypeRef) extends Floating(delegate)

  object Half {
    def apply(half: LLVMTypeRef): Half = {
      Objects.requireNonNull(half)
      if (Kind(LLVMGetTypeKind(half)) != Kind.Half)
        throw new IllegalArgumentException
      new Half(half)
    }

    def apply()(implicit context: Context): Half = new Half(LLVMHalfTypeInContext(context.delegate))
  }

  class Float(override val delegate: LLVMTypeRef) extends Floating(delegate)

  object Float {
    def apply(float: LLVMTypeRef): Float = {
      Objects.requireNonNull(float)
      if (Kind(LLVMGetTypeKind(float)) != Kind.Float)
        throw new IllegalArgumentException
      new Float(float)
    }

    def apply()(implicit context: Context): Float = new Float(LLVMFloatTypeInContext(context.delegate))
  }

  class Double(override val delegate: LLVMTypeRef) extends Floating(delegate)

  object Double {
    def apply(double: LLVMTypeRef): Double = {
      Objects.requireNonNull(double)
      if (Kind(LLVMGetTypeKind(double)) != Kind.Double)
        throw new IllegalArgumentException
      new Double(double)
    }

    def apply()(implicit context: Context): Double = new Double(LLVMDoubleTypeInContext(context.delegate))
  }

  class X86FP80(override val delegate: LLVMTypeRef) extends Floating(delegate)

  object X86FP80 {
    def apply(fp: LLVMTypeRef): X86FP80 = {
      Objects.requireNonNull(fp)
      if (Kind(LLVMGetTypeKind(fp)) != Kind.X86_FP80)
        throw new IllegalArgumentException
      new X86FP80(fp)
    }

    def apply()(implicit context: Context): X86FP80 = new X86FP80(LLVMX86FP80TypeInContext(context.delegate))
  }

  class FP128(override val delegate: LLVMTypeRef) extends Floating(delegate)

  object FP128 {
    def apply(fp: LLVMTypeRef): FP128 = {
      Objects.requireNonNull(fp)
      if (Kind(LLVMGetTypeKind(fp)) != Kind.FP128)
        throw new IllegalArgumentException
      new FP128(fp)
    }

    def apply()(implicit context: Context): FP128 = new FP128(LLVMFP128TypeInContext(context.delegate))
  }

  class PPCFP128(override val delegate: LLVMTypeRef) extends Floating(delegate)

  object PPCFP128 {
    def apply(fp: LLVMTypeRef): PPCFP128 = {
      Objects.requireNonNull(fp)
      if (Kind(LLVMGetTypeKind(fp)) != Kind.PPC_FP128)
        throw new IllegalArgumentException
      new PPCFP128(fp)
    }

    def apply()(implicit context: Context): PPCFP128 = new PPCFP128(LLVMPPCFP128TypeInContext(context.delegate))
  }

  def half()(implicit context: Context): Half = Half()(context)

  def float()(implicit context: Context): Float = Float()(context)

  def double()(implicit context: Context): Double = Double()(context)

  def x86fp80()(implicit context: Context): X86FP80 = X86FP80()(context)

  def fp128()(implicit context: Context): FP128 = FP128()(context)

  def ppcfp128()(implicit context: Context): PPCFP128 = PPCFP128()(context)

  // function types

  class Function private[llvm](
                                override val delegate: LLVMTypeRef,
                                private var _rt: Type,
                                private var _pts: Seq[Type]
                              ) extends Type(delegate) {
    def this(delegate: LLVMTypeRef) {
      this(delegate, null, null)
    }

    def returnType: Type = {
      if (_rt != null) {
        _rt
      } else {
        _rt = Type(LLVMGetReturnType(delegate))
        _rt
      }
    }

    def paramTypes: Seq[Type] = {
      if (_pts != null)
        _pts
      else {
        val i = LLVMCountParamTypes(delegate)
        if (i != 0) {
          val pp = new PointerPointer[LLVMTypeRef](i)
          LLVMGetParamTypes(delegate, pp)
          _pts = (0 until i).map(index => Type(pp.get(classOf[LLVMTypeRef], index)))
          _pts
        } else {
          _pts = Seq()
          _pts
        }
      }
    }

    lazy val isVararg: Boolean = LLVMIsFunctionVarArg(delegate) != 0
  }

  object Function {
    def apply(funTypeRef: LLVMTypeRef): Function = {
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
        LLVMFunctionType(returnType.delegate, ps(0), ps.length, if (isVarargs) 1 else 0),
        returnType,
        paramTypes
      )
    else
      new Function(
        LLVMFunctionType(returnType.delegate, new scala.Array[LLVMTypeRef](1)(0), 0, if (isVarargs) 1 else 0),
        returnType,
        paramTypes
      )
  }

  // Structure Types

  class Struct(override val delegate: LLVMTypeRef) extends Type(delegate) {
    def elementCount: Int = LLVMCountStructElementTypes(delegate)

    def elementAt(idx: Int): Type = Type(LLVMStructGetTypeAtIndex(delegate, idx))

    def elementTypes: Seq[Type] = {
      val count = elementCount
      val pp = new PointerPointer[LLVMTypeRef](count)
      LLVMGetStructElementTypes(delegate, pp)
      (0 until count).map(idx => Type(pp.get(classOf[LLVMTypeRef], idx)))
    }

    def isPacked: Boolean = LLVMIsPackedStruct(delegate) != 0

    def body(elementTypes: Seq[Type], packed: Boolean = false): Struct.this.type = {
      val arr: scala.Array[LLVMTypeRef] = elementTypes.view.map(_.delegate).toArray
      if (arr.length != 0) {
        val pp = new PointerPointer[LLVMTypeRef](arr.length)
        pp.put(arr: _*)
        LLVMStructSetBody(
          delegate,
          pp,
          arr.length,
          if (packed) 1 else 0)
      }
      else
        LLVMStructSetBody(
          delegate,
          new scala.Array[LLVMTypeRef](1)(0),
          arr.length,
          if (packed) 1 else 0)
      this
    }
  }

  object Struct {
    def apply(structTypeRef: LLVMTypeRef): Struct = {
      Objects.requireNonNull(structTypeRef)
      if (Kind(LLVMGetTypeKind(structTypeRef)) != Kind.Struct)
        throw new IllegalArgumentException

      new Struct(structTypeRef)
    }

    def apply(name: String)(implicit context: Context): Struct = {
      Objects.requireNonNull(name)
      new Struct(LLVMStructCreateNamed(context.delegate, name))
    }

    def apply(elementTypes: Seq[Type], packed: Boolean = false)(implicit context: Context): Struct = {
      val arr: scala.Array[LLVMTypeRef] = elementTypes.view.map(_.delegate).toArray
      if (arr.length != 0) {
        val pp = new PointerPointer[LLVMTypeRef](arr.length)
        pp.put(arr: _*)
        new Struct(LLVMStructTypeInContext(
          context.delegate,
          pp,
          arr.length,
          if (packed) 1 else 0))
      }
      else
        new Struct(LLVMStructTypeInContext(
          context.delegate,
          new scala.Array[LLVMTypeRef](1)(0),
          arr.length,
          if (packed) 1 else 0))
    }
  }

  def struct(elementTypes: Type*)(implicit context: Context): Struct =
    Struct(elementTypes)(context)

  // Sequential Types

  abstract sealed class Sequential(override val delegate: LLVMTypeRef) extends Type(delegate) {
    lazy val elementType: Type = Type(LLVMGetElementType(delegate))
  }

  object Sequential {
    def apply(seqTypeRef: LLVMTypeRef): Sequential = {
      Objects.requireNonNull(seqTypeRef)
      Kind(LLVMGetTypeKind(seqTypeRef)) match {
        case Kind.Array => new Array(seqTypeRef)
        case Kind.Pointer => new Pointer(seqTypeRef)
        case Kind.Vector => new Vector(seqTypeRef)
        case _ => throw new IllegalArgumentException
      }
    }
  }

  class Array(override val delegate: LLVMTypeRef) extends Sequential(delegate) {
    lazy val length: Int = LLVMGetArrayLength(delegate)
  }

  object Array {
    def apply(arrTypeRef: LLVMTypeRef): Array = {
      Objects.requireNonNull(arrTypeRef)
      if (Kind(LLVMGetTypeKind(arrTypeRef)) != Kind.Array)
        throw new IllegalArgumentException
      new Array(arrTypeRef)
    }

    def apply(elementType: Type, elementCount: Int): Array = {
      Objects.requireNonNull(elementType)
      new Array(LLVMArrayType(elementType.delegate, elementCount))
    }
  }

  class Pointer(override val delegate: LLVMTypeRef) extends Sequential(delegate) {
    lazy val addressSpace: Int = LLVMGetPointerAddressSpace(delegate)
  }

  object Pointer {
    def apply(pointerTypeRef: LLVMTypeRef): Pointer = {
      Objects.requireNonNull(pointerTypeRef)
      if (Kind(LLVMGetTypeKind(pointerTypeRef)) != Kind.Pointer)
        throw new IllegalArgumentException
      new Pointer(pointerTypeRef)
    }

    def apply(elementType: Type, addressSpace: Int = 1): Pointer = {
      Objects.requireNonNull(elementType)
      new Pointer(LLVMVectorType(elementType.delegate, addressSpace))
    }
  }

  class Vector(override val delegate: LLVMTypeRef) extends Sequential(delegate) {
    lazy val vectorSize: Int = LLVMGetVectorSize(delegate)
  }

  object Vector {
    def apply(vectorTypeRef: LLVMTypeRef): Vector = {
      Objects.requireNonNull(vectorTypeRef)
      if (Kind(LLVMGetTypeKind(vectorTypeRef)) != Kind.Vector)
        throw new IllegalArgumentException
      new Vector(vectorTypeRef)
    }

    def apply(elementType: Type, elementCount: Int): Vector = {
      Objects.requireNonNull(elementType)
      new Vector(LLVMVectorType(elementType.delegate, elementCount))
    }
  }

  def array(elementType: Type, elementCount: Int): Array = Array(elementType, elementCount)

  def pointer(elementType: Type, addressSpace: Int = 1): Pointer = Pointer(elementType, addressSpace)

  def vector(elementType: Type, elementCount: Int): Vector = Vector(elementType, elementCount)

  // Other Types

  class Label(override val delegate: LLVMTypeRef) extends Type(delegate)

  object Label {
    def apply(labelTypeRef: LLVMTypeRef): Label = {
      Objects.requireNonNull(labelTypeRef)
      if (Kind(LLVMGetTypeKind(labelTypeRef)) != Kind.Label)
        throw new IllegalArgumentException
      new Label(labelTypeRef)
    }

    def apply()(implicit context: Context): Label =
      new Label(LLVMLabelTypeInContext(context.delegate))
  }

  class Void(override val delegate: LLVMTypeRef) extends Type(delegate)

  object Void {
    def apply(labelTypeRef: LLVMTypeRef): Void = {
      Objects.requireNonNull(labelTypeRef)
      if (Kind(LLVMGetTypeKind(labelTypeRef)) != Kind.Void)
        throw new IllegalArgumentException
      new Void(labelTypeRef)
    }

    def apply()(implicit context: Context): Void =
      new Void(LLVMVoidTypeInContext(context.delegate))
  }

  def label(implicit context: Context): Label = Label()(context)

  def void()(implicit context: Context): Void = Void()(context)


  class Unknown(override val delegate: LLVMTypeRef) extends Type(delegate)

  object Unknown {
    def apply(typeRef: LLVMTypeRef): Unknown = new Unknown(typeRef)
  }

}
