package org.glavo.llvm.core

import java.util.Objects

import org.bytedeco.javacpp.LLVM._

case class Context(delegate: LLVMContextRef = LLVMContextCreate()) {
  implicit def context: Context = this

  Objects.requireNonNull(delegate)
  //todo
  override def finalize(): Unit = LLVMContextDispose(delegate)
}

object Context {
  implicit val Global: Context = Context(LLVMGetGlobalContext())

  def apply(contextRef: LLVMContextRef = LLVMContextCreate()): Context = new Context(contextRef)

}