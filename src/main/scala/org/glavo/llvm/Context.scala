package org.glavo.llvm

import java.util.Objects

import org.bytedeco.javacpp.LLVM

case class Context(delegate: LLVM.LLVMContextRef = LLVM.LLVMContextCreate()) {
  implicit def context: Context = this

  Objects.requireNonNull(delegate)
  //todo
  override def finalize(): Unit = LLVM.LLVMContextDispose(delegate)
}

object Context {
  implicit val Global: Context = Context(LLVM.LLVMGetGlobalContext())

  def apply(contextRef: LLVM.LLVMContextRef = LLVM.LLVMContextCreate()): Context = new Context(contextRef)

}