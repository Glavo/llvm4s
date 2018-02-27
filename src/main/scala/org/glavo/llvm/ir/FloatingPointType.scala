package org.glavo.llvm.ir

import org.glavo.llvm.internal.ir.TypeImpl

class FloatingPointType(handle: Long) extends Type(handle) {

}

object FloatingPointType {
  def half(implicit context: Context): FloatingPointType =
    Type(TypeImpl.getHalfTy(context.handle)).asInstanceOf[FloatingPointType]

  def float(implicit context: Context): FloatingPointType =
    Type(TypeImpl.getFloatTy(context.handle)).asInstanceOf[FloatingPointType]

  def double(implicit context: Context): FloatingPointType =
    Type(TypeImpl.getDoubleTy(context.handle)).asInstanceOf[FloatingPointType]

  def x86_fp80(implicit context: Context): FloatingPointType =
    Type(TypeImpl.getX86_MMXTy(context.handle)).asInstanceOf[FloatingPointType]

  def fp128(implicit context: Context): FloatingPointType =
    Type(TypeImpl.getFP128Ty(context.handle)).asInstanceOf[FloatingPointType]

  def ppc_fp128(implicit context: Context): FloatingPointType =
    Type(TypeImpl.getPPC_FP128Ty(context.handle)).asInstanceOf[FloatingPointType]
}