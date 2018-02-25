package org.glavo.llvm

import org.bytedeco.javacpp.LLVM

case class Module(delegate: LLVM.LLVMModuleRef)
  extends Cloneable {
  override def clone(): Module = new Module(LLVM.LLVMCloneModule(delegate))
}

object Module {
  def apply(moduleRef: LLVM.LLVMModuleRef): Module =
    if (moduleRef != null) new Module(moduleRef) else null

  def apply(moduleId: String)(implicit context: Context): Module =
    new Module(LLVM.LLVMModuleCreateWithNameInContext(moduleId, context.delegate))
}