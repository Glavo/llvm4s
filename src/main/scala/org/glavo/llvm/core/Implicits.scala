package org.glavo.llvm.core

import org.bytedeco.javacpp.LLVM

import scala.language.implicitConversions

trait Implicits {
  implicit final def llvmModuleWrapper(moduleRef: LLVM.LLVMModuleRef): Module =
    Module(moduleRef)
}

object Implicits extends Implicits