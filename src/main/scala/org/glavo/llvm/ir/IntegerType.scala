package org.glavo.llvm.ir

import org.glavo.llvm.internal.Unsigned
import org.glavo.llvm.internal.ir.IntegerTypeImpl

class IntegerType private[llvm](handle: Long) extends Type(handle) {
  def bitWidth: Int@Unsigned = IntegerTypeImpl.getBitWidth(handle)

  def bitMask: Long@Unsigned = IntegerTypeImpl.getBitMask(handle)

  def signBit: Long@Unsigned = IntegerTypeImpl.getSignBit(handle)

  def isPowerOf2ByteWidth: Boolean = IntegerTypeImpl.isPowerOf2ByteWidth(handle)
}

object IntegerType {
  val MinIntBits: Int = 1
  val MaxIntBits: Int = (1 << 24) - 1

  def apply(numBits: Int@Unsigned)(implicit context: Context): IntegerType =
     Type(IntegerTypeImpl.get(context.handle, numBits)).asInstanceOf[IntegerType]

}
