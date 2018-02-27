package org.glavo.llvm.ir

import org.glavo.llvm.internal.ir.TypeImpl

class VoidType(handle: Long) extends Type(handle) {

}

object VoidType {
  def apply()(implicit context: Context): VoidType =
    Type(TypeImpl.getVoidTy(context.handle)).asInstanceOf[VoidType]
}