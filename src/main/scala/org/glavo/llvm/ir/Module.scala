package org.glavo.llvm.ir

import java.util.Objects

import org.glavo.llvm.internal.ir.ModuleImpl
import org.glavo.llvm.Destructor
import org.glavo.llvm.internal.JNIUtils

class Module private[llvm](private[llvm] var handle: Long,
                           private[llvm] val destructor: Destructor[Module] = null) {
  private def checkHandle(): Unit = if (handle == 0) throw new NullPointerException

  def id: String = {
    checkHandle()
    JNIUtils.bytes2str(ModuleImpl.getId(handle))
  }

  def id_=(id: String): Unit = {
    checkHandle()
    Objects.requireNonNull(id)
    ModuleImpl.setId(handle, JNIUtils.str2bytes(id))
  }

  def name: String = {
    checkHandle()
    JNIUtils.bytes2str(ModuleImpl.getName(handle))
  }

  def sourceFileName: String = {
    checkHandle()
    JNIUtils.bytes2str(ModuleImpl.getSourceFileName(handle))
  }

  def sourceFileName_=(name: String): Unit = {
    checkHandle()
    Objects.requireNonNull(name)
    ModuleImpl.setSourceFileName(handle, JNIUtils.str2bytes(name))
  }

  val context: Context = {
    checkHandle()
    ModuleImpl.getContext(handle) match {
      case 0 => null
      case h if h == Context.Global.handle => Context.Global
      case h => new Context(h)
    }
  }

  override def toString: String = s"Module(id=$id,sourceFileName=$sourceFileName)"

  override def hashCode(): Int = handle.toInt

  override def equals(obj: scala.Any): Boolean = obj match {
    case m: Module => this.handle == m.handle
    case _ => false
  }

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
    new Module(ModuleImpl.newInstance(JNIUtils.str2bytes(name), context.handle), defaultDestructor)
}