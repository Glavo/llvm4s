package org.glavo.llvm.core

import java.util.Objects

import org.bytedeco.javacpp.LLVM._

case class Module(delegate: LLVMModuleRef)
  extends Cloneable {
  Objects.requireNonNull(delegate)


  override def clone(): Module = new Module(LLVMCloneModule(delegate))

}

object Module {
  def apply(moduleRef: LLVMModuleRef): Module =
    new Module(moduleRef)

  def apply(moduleId: String)(implicit context: Context): Module =
    new Module(LLVMModuleCreateWithNameInContext(moduleId, context.delegate))
}