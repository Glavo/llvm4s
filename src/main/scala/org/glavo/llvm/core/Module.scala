package org.glavo.llvm.core

import java.util.Objects

import org.bytedeco.javacpp.LLVM

case class Module(delegate: LLVM.LLVMModuleRef)
  extends Cloneable {
  Objects.requireNonNull(delegate)


  override def clone(): Module = new Module(LLVM.LLVMCloneModule(delegate))

}

object Module {
  def apply(moduleRef: LLVM.LLVMModuleRef): Module =
    new Module(moduleRef)

  def apply(moduleId: String)(implicit context: Context): Module =
    new Module(LLVM.LLVMModuleCreateWithNameInContext(moduleId, context.delegate))
}