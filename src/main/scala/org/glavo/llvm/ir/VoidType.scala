package org.glavo.llvm.ir

class VoidType(handle: Long) extends Type(handle) {

}

object VoidType {
  def apply()(implicit context: Context): VoidType =
    Type(TypeImpl.getVoidTy(context.handle)).asInstanceOf[VoidType]
}