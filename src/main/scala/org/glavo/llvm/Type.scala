package org.glavo.llvm

import java.util.Objects

import org.bytedeco.javacpp.{LLVM, PointerPointer}

import scala.annotation.switch

case class Type(delegate: LLVM.LLVMTypeRef) {

  lazy val kind: Type.Kind = Type.Kind(LLVM.LLVMGetTypeKind(delegate))

  lazy val context: Context =
    new Context(Objects.requireNonNull(LLVM.LLVMGetTypeContext(delegate)))

  def isFunction: Boolean = kind == Type.Kind.Function

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
  def apply(typeRef: LLVM.LLVMTypeRef): Type =
    if (typeRef != null) new Type(typeRef) else null

  class Int1(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Int1 {
    def apply(int: LLVM.LLVMTypeRef): Int1 =
      if (int != null) new Int1(int) else null

    def apply()(implicit context: Context): Int1 =
      int1(context)
  }

  class Int8(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Int8 {
    def apply(int: LLVM.LLVMTypeRef): Int8 =
      if (int != null) new Int8(int) else null

    def apply()(implicit context: Context): Int8 =
      int8(context)
  }

  class Int16(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Int16 {
    def apply(int: LLVM.LLVMTypeRef): Int16 =
      if (int != null) new Int16(int) else null

    def apply()(implicit context: Context): Int16 =
      int16(context)
  }

  class Int32(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Int32 {
    def apply(int: LLVM.LLVMTypeRef): Int32 =
      if (int != null) new Int32(int) else null

    def apply()(implicit context: Context): Int32 =
      int32(context)
  }

  class Int64(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Int64 {
    def apply(int: LLVM.LLVMTypeRef): Int64 =
      if (int != null) new Int64(int) else null

    def apply()(implicit context: Context): Int64 =
      int64(context)
  }

  class Int128(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Int128 {
    def apply(int: LLVM.LLVMTypeRef): Int128 =
      if (int != null) new Int128(int) else null

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

  class Function(override val delegate: LLVM.LLVMTypeRef) extends Type(delegate)

  object Function {
    def apply(funTypeRef: LLVM.LLVMTypeRef): Function =
      if (funTypeRef != null) new Function(funTypeRef) else null

    def apply(returnType: Type, paramTypes: Seq[Type], isVarargs: Boolean = false): Function =
      function(returnType, paramTypes, isVarargs)
  }

  def function(returnType: Type, paramTypes: Seq[Type], isVarargs: Boolean = false): Function = {
    Objects.requireNonNull(returnType)
    Objects.requireNonNull(paramTypes)
    val ps = paramTypes.view.map(_.delegate).toArray

    if (ps.length != 0)
      new Function(LLVM.LLVMFunctionType(returnType.delegate, ps(0), ps.length, if (isVarargs) 1 else 0))
    else
      new Function(LLVM.LLVMFunctionType(returnType.delegate, new Array[LLVM.LLVMTypeRef](1)(0), 0, if (isVarargs) 1 else 0))
  }
}
