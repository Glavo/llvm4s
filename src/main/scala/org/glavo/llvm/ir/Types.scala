package org.glavo.llvm.ir

import org.glavo.llvm.internal.Unsigned

object Types {
  def pointer(elemType: Type, addressSpace: Int@Unsigned = 0): PointerType =
    PointerType(elemType, addressSpace)

  case class fun(parameterTypes: Type*) {
    def ->(retType: Type): FunctionType = FunctionType(retType, parameterTypes)
  }

  def void(implicit context: Context): VoidType = VoidType()

  def half(implicit context: Context): FloatingPointType =
    FloatingPointType.half

  def float(implicit context: Context): FloatingPointType =
    FloatingPointType.float

  def double(implicit context: Context): FloatingPointType =
    FloatingPointType.double

  def x86_fp80(implicit context: Context): FloatingPointType =
    FloatingPointType.x86_fp80

  def fp128(implicit context: Context): FloatingPointType =
    FloatingPointType.fp128

  def ppc_fp128(implicit context: Context): FloatingPointType =
    FloatingPointType.ppc_fp128

  def metadata(implicit context: Context): MetadataType =
    MetadataType()

  def x86_mmx(implicit context: Context): X86_MMXType =
    X86_MMXType()

  def token(implicit context: Context): TokenType =
    TokenType()

  def int1(implicit context: Context): IntegerType = IntegerType(1)

  def int8(implicit context: Context): IntegerType = IntegerType(8)

  def int16(implicit context: Context): IntegerType = IntegerType(16)

  def int32(implicit context: Context): IntegerType = IntegerType(32)

  def int64(implicit context: Context): IntegerType = IntegerType(64)

  def int128(implicit context: Context): IntegerType = IntegerType(128)

  def int(numBits: Int@Unsigned)(implicit context: Context): IntegerType = IntegerType(numBits)
}