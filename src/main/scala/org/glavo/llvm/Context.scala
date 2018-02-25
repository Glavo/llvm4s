package org.glavo.llvm

import org.bytedeco.javacpp.LLVM

case class Context(delegate: LLVM.LLVMContextRef = LLVM.LLVMContextCreate()) {

}

object Context {
  def apply(contextRef: LLVM.LLVMContextRef): Context =
    if(contextRef != null) new Context(contextRef) else null

  implicit val Global: Context = Context(LLVM.LLVMGetGlobalContext())
}