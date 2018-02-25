package org.glavo.llvm

import java.util.Objects

import org.bytedeco.javacpp.{LLVM, PointerPointer}

import scala.annotation.switch

abstract case class Type(delegate: LLVM.LLVMTypeRef, var _context: Context) {
  def this(delegate: LLVM.LLVMTypeRef) {
    this(delegate, null)
  }

  Objects.requireNonNull(delegate)

  lazy val kind: Type.Kind = Type.Kind(LLVM.LLVMGetTypeKind(delegate))

  lazy val context: Context = {
    if (_context != null)
      _context
    else
      new Context(Objects.requireNonNull(LLVM.LLVMGetTypeContext(delegate)))
  }

  lazy val isSized: Boolean = if (LLVM.LLVMTypeIsSized(delegate) != 0) true else false

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
      case Kind.Half => new Half(typeRef)
      case Kind.Float => new Float(typeRef)
      case Kind.Double => new Double(typeRef)
      case Kind.X86_FP80 => new X86FP80(typeRef)
      case Kind.FP128 => new FP128(typeRef)
      case Kind.PPC_FP128 => new PPCFP128(typeRef)
      case Kind.Function => new Function(typeRef)
      //todo
      case _ => Unknown(typeRef)
    }
  }

  // Integer Types

  sealed class Integer(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate) {
    lazy val width: Int = LLVM.LLVMGetIntTypeWidth(delegate)
  }

  object Integer {
    def apply(intTypeRef: LLVM.LLVMTypeRef): Integer = {
      Objects.requireNonNull(intTypeRef)
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
    def apply(int: LLVM.LLVMTypeRef): Int1 = {
      Objects.requireNonNull(int)
      if (Kind(LLVM.LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int1(int)
    }

    def apply()(implicit context: Context): Int1 =
      int1(context)
  }

  class Int8(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int8 {
    def apply(int: LLVM.LLVMTypeRef): Int8 = {
      Objects.requireNonNull(int)
      if (Kind(LLVM.LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int8(int)
    }

    def apply()(implicit context: Context): Int8 =
      int8(context)
  }

  class Int16(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int16 {
    def apply(int: LLVM.LLVMTypeRef): Int16 = {
      Objects.requireNonNull(int)
      if (Kind(LLVM.LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int16(int)
    }

    def apply()(implicit context: Context): Int16 =
      int16(context)
  }

  class Int32(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int32 {
    def apply(int: LLVM.LLVMTypeRef): Int32 = {
      Objects.requireNonNull(int)
      if (Kind(LLVM.LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int32(int)
    }

    def apply()(implicit context: Context): Int32 =
      int32(context)
  }

  class Int64(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int64 {
    def apply(int: LLVM.LLVMTypeRef): Int64 = {
      Objects.requireNonNull(int)
      if (Kind(LLVM.LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int64(int)
    }

    def apply()(implicit context: Context): Int64 =
      int64(context)
  }

  class Int128(override val delegate: LLVM.LLVMTypeRef) extends Integer(delegate)

  object Int128 {
    def apply(int: LLVM.LLVMTypeRef): Int128 = {
      Objects.requireNonNull(int)
      if (Kind(LLVM.LLVMGetTypeKind(int)) != Kind.Integer)
        throw new IllegalArgumentException
      new Int128(int)
    }

    def apply()(implicit context: Context): Int128 =
      int128(context)
  }

  @inline
  def integer(numBits: Int)(implicit context: Context): Integer = Integer(numBits)(context)

  @inline
  def int1(implicit context: Context): Int1 = new Int1(LLVM.LLVMInt1TypeInContext(context.delegate))

  @inline
  def int8(implicit context: Context): Int8 = new Int8(LLVM.LLVMInt8TypeInContext(context.delegate))

  @inline
  def int16(implicit context: Context): Int16 = new Int16(LLVM.LLVMInt16TypeInContext(context.delegate))

  @inline
  def int32(implicit context: Context): Int32 = new Int32(LLVM.LLVMInt32TypeInContext(context.delegate))

  @inline
  def int64(implicit context: Context): Int64 = new Int64(LLVM.LLVMInt64TypeInContext(context.delegate))

  @inline
  def int128(implicit context: Context): Int128 = new Int128(LLVM.LLVMInt128TypeInContext(context.delegate))

  // Floating Types

  sealed class Floating(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Floating {
    def apply(floating: LLVM.LLVMTypeRef): Floating = {
      Objects.requireNonNull(floating)
      Kind(LLVM.LLVMGetTypeKind(floating)) match {
        case Kind.Half => new Half(floating)
        case Kind.Float => new Float(floating)
        case Kind.Double => new Double(floating)
        case Kind.X86_FP80 => new X86FP80(floating)
        case Kind.FP128 => new FP128(floating)
        case Kind.PPC_FP128 => new PPCFP128(floating)
      }
    }
  }

  class Half(override val delegate: LLVM.LLVMTypeRef) extends Floating(delegate)

  object Half {
    def apply(half: LLVM.LLVMTypeRef): Half = {
      Objects.requireNonNull(half)
      if (Kind(LLVM.LLVMGetTypeKind(half)) != Kind.Half)
        throw new IllegalArgumentException
      new Half(half)
    }

    def apply()(implicit context: Context): Half = new Half(LLVM.LLVMHalfTypeInContext(context.delegate))
  }

  class Float(override val delegate: LLVM.LLVMTypeRef) extends Floating(delegate)

  object Float {
    def apply(float: LLVM.LLVMTypeRef): Float = {
      Objects.requireNonNull(float)
      if (Kind(LLVM.LLVMGetTypeKind(float)) != Kind.Float)
        throw new IllegalArgumentException
      new Float(float)
    }

    def apply()(implicit context: Context): Float = new Float(LLVM.LLVMFloatTypeInContext(context.delegate))
  }

  class Double(override val delegate: LLVM.LLVMTypeRef) extends Floating(delegate)

  object Double {
    def apply(double: LLVM.LLVMTypeRef): Double = {
      Objects.requireNonNull(double)
      if (Kind(LLVM.LLVMGetTypeKind(double)) != Kind.Double)
        throw new IllegalArgumentException
      new Double(double)
    }

    def apply()(implicit context: Context): Double = new Double(LLVM.LLVMDoubleTypeInContext(context.delegate))
  }

  class X86FP80(override val delegate: LLVM.LLVMTypeRef) extends Floating(delegate)

  object X86FP80 {
    def apply(fp: LLVM.LLVMTypeRef): X86FP80 = {
      Objects.requireNonNull(fp)
      if (Kind(LLVM.LLVMGetTypeKind(fp)) != Kind.X86_FP80)
        throw new IllegalArgumentException
      new X86FP80(fp)
    }

    def apply()(implicit context: Context): X86FP80 = new X86FP80(LLVM.LLVMX86FP80TypeInContext(context.delegate))
  }

  class FP128(override val delegate: LLVM.LLVMTypeRef) extends Floating(delegate)

  object FP128 {
    def apply(fp: LLVM.LLVMTypeRef): FP128 = {
      Objects.requireNonNull(fp)
      if (Kind(LLVM.LLVMGetTypeKind(fp)) != Kind.FP128)
        throw new IllegalArgumentException
      new FP128(fp)
    }

    def apply()(implicit context: Context): FP128 = new FP128(LLVM.LLVMFP128TypeInContext(context.delegate))
  }

  class PPCFP128(override val delegate: LLVM.LLVMTypeRef) extends Floating(delegate)

  object PPCFP128 {
    def apply(fp: LLVM.LLVMTypeRef): PPCFP128 = {
      Objects.requireNonNull(fp)
      if (Kind(LLVM.LLVMGetTypeKind(fp)) != Kind.PPC_FP128)
        throw new IllegalArgumentException
      new PPCFP128(fp)
    }

    def apply()(implicit context: Context): PPCFP128 = new PPCFP128(LLVM.LLVMPPCFP128TypeInContext(context.delegate))
  }

  def half()(implicit context: Context): Half = Half()(context)

  def float()(implicit context: Context): Float = Float()(context)

  def double()(implicit context: Context): Double = Double()(context)

  def x86fp80()(implicit context: Context): X86FP80 = X86FP80()(context)

  def fp128()(implicit context: Context): FP128 = FP128()(context)

  def ppcfp128()(implicit context: Context): PPCFP128 = PPCFP128()(context)

  // function types

  class Function private[llvm](
                                override val delegate: LLVM.LLVMTypeRef,
                                private var _rt: Type,
                                private var _pts: Seq[Type]
                              ) extends Type(delegate) {
    def this(delegate: LLVM.LLVMTypeRef) {
      this(delegate, null, null)
    }

    def returnType: Type = {
      if (_rt != null) {
        _rt
      } else {
        _rt = Type(LLVM.LLVMGetReturnType(delegate))
        _rt
      }
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

    lazy val isVararg: Boolean = if (LLVM.LLVMIsFunctionVarArg(delegate) != 0) true else false
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
        returnType,
        paramTypes
      )
    else
      new Function(
        LLVM.LLVMFunctionType(returnType.delegate, new Array[LLVM.LLVMTypeRef](1)(0), 0, if (isVarargs) 1 else 0),
        returnType,
        paramTypes
      )
  }

  // Structure Types



  //todo
  class Unknown(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Unknown {
    def apply(typeRef: LLVM.LLVMTypeRef): Unknown = new Unknown(typeRef)
  }

}
