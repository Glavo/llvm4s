package org.glavo.llvm.ir

import org.glavo.llvm.{Destructor, Unsigned}

class IntegerType private[llvm](handle: Long) extends Type(handle) {
  def bitWidth: Int@Unsigned = IntegerTypeImpl.getBitWidth(handle)

  def bitMask: Long@Unsigned = IntegerTypeImpl.getBitMask(handle)

  def isPowerOf2ByteWidth: Boolean = IntegerTypeImpl.isPowerOf2ByteWidth(handle)
}

object IntegerType {
  val MinIntBits: Int = 1
  val MaxIntBits: Int = (1 << 24) - 1
}
