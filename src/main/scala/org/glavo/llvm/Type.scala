package org.glavo.llvm

import java.util.Objects

import org.bytedeco.javacpp.LLVM

case class Type(delegate: LLVM.LLVMTypeRef) {
}

object Type {
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
    Function(LLVM.LLVMFunctionType(returnType.delegate, ps(0), ps.length, if (isVarargs) 1 else 0))
  }
}
