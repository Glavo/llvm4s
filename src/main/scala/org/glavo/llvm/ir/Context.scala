package org.glavo.llvm.ir

import org.glavo.llvm.Destructor

class Context private[llvm](
                             private[llvm] var handle: Long,
                             private[llvm] var destructor: Destructor[Context] = Context.defaultDestructor) {
  override def finalize(): Unit = {
    if (destructor != null) {
      destructor(this)
    }
  }

  override def toString: String = f"Context(address=0x$handle%x)"
}

object Context {
  implicit val Global: Context = new Context(ContextImpl.getGlobal, null)

  val defaultDestructor: Destructor[Context] = context => {
    if (context.handle != 0) {
      ModuleImpl.delete(context.handle)
      context.handle = 0
    }
  }

  def apply(): Context = new Context(ContextImpl.newInstance)
}
