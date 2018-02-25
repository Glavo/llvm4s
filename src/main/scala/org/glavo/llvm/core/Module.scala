package org.glavo.llvm.core

import java.util.Objects

import org.bytedeco.javacpp.LLVM._
import org.bytedeco.javacpp.{Pointer, SizeTPointer}

case class Module(delegate: LLVMModuleRef)
  extends Cloneable {
  Objects.requireNonNull(delegate)


  def identifier: String = {
    val p = Pointer.malloc(8)
    val sizep = new SizeTPointer(p)
    val s = LLVMGetModuleIdentifier(delegate, sizep)
      .capacity(sizep.get())
      .getString(org.glavo.llvm.LLVMEncoding)
    Pointer.free(p)
    s
  }

  def getFunction(name: String): Value =
    Option(LLVMGetNamedFunction(delegate, name)).map(Value.apply).orNull

  def target: String = LLVMGetTarget(delegate).getString(org.glavo.llvm.LLVMEncoding)

  def target_=(tripie: String): Unit = LLVMSetTarget(delegate, tripie)

  def dataLayout: String = LLVMGetDataLayout(delegate).getString(org.glavo.llvm.LLVMEncoding)

  def dataLayout_=(layout: String): Unit = LLVMSetDataLayout(delegate, layout)

  override def clone(): Module = new Module(LLVMCloneModule(delegate))

  override def toString: String = {
    val bs = LLVMPrintModuleToString(delegate)
    val s = bs.getString(org.glavo.llvm.LLVMEncoding)
    LLVMDisposeMessage(bs)
    s"Module($s)"
  }
}

object Module {
  def apply(moduleRef: LLVMModuleRef): Module =
    new Module(moduleRef)

  def apply(moduleId: String)(implicit context: Context): Module =
    new Module(LLVMModuleCreateWithNameInContext(moduleId, context.delegate))
}