package org.glavo.llvm.ir

import org.glavo.llvm.{Destructor, JNIUtils}

class Module private[llvm](
                            private[llvm] var handle: Long,
                            private[llvm] val destructor: Destructor[Module] = Module.defaultDestructor) {
  def id: String =
    if (handle != 0) JNIUtils.bytes2str(ModuleImpl.getId(handle))
    else throw new NullPointerException

  def id_=(id: String): Unit =
    if (handle != 0) ModuleImpl.setId(handle, JNIUtils.str2bytes(id))
    else throw new NullPointerException

  def name: String =
    if (handle != 0) JNIUtils.bytes2str(ModuleImpl.getName(handle))
    else throw new NullPointerException



  override def toString: String = s"Module(id=$id)"

  override def finalize(): Unit = {
    if (destructor != null)
      destructor(this)
  }
}

object Module {
  val defaultDestructor: Destructor[Module] = module => {
    if (module.handle != 0) {
      ModuleImpl.delete(module.handle)
      module.handle = 0
    }
  }

  def apply(name: String)(implicit context: Context): Module =
    new Module(ModuleImpl.newInstance(JNIUtils.str2bytes(name), context.handle))
}