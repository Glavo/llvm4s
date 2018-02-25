package org.glavo.llvm.core

import java.util.Objects

import org.bytedeco.javacpp.LLVM._

case class Use(delegate: LLVMUseRef) {
  Objects.requireNonNull(delegate)

  def user: Value = Value(LLVMGetUser(delegate))

  def usedValue: Value = Value(LLVMGetUsedValue(delegate))

  def nextUse: Use = Use(LLVMGetNextUse(delegate))
}
