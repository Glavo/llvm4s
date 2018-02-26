package org.glavo.llvm.ir

import org.glavo.llvm.Destructor

class Context private[llvm](private[llvm] var handle: Long,
                            private[llvm] var destructor: Destructor[Context] = null) {

  object Types {

  }

  override def finalize(): Unit = {
    if (destructor != null) {
      destructor(this)
    }
  }

  override def equals(obj: scala.Any): Boolean = obj match {
    case c: Context => this.handle == c.handle
    case _ => false
  }

  override def toString: String = f"Context(address=0x$handle%x)"
}

object Context {
  val defaultDestructor: Destructor[Context] = context => {
    if (context.handle != 0) {
      ModuleImpl.delete(context.handle)
      context.handle = 0
    }
  }

  implicit val Global: Context = new Context(ContextImpl.getGlobal)

  def apply(): Context = new Context(ContextImpl.newInstance, defaultDestructor)
}
