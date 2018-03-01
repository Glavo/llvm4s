package org.glavo.llvm.ir

import org.glavo.llvm.internal.Unsigned
import org.glavo.llvm.internal.ir.VectorTypeImpl

class VectorType private[llvm](handle: Long) extends SequentialType(handle) {
  val bitWidth: Int@Unsigned = VectorTypeImpl.getBitWidth(handle)

  override def toString: String = s"VectorType($mkString)"
}

object VectorType {
  def apply(elemType: Type, numElements: Int@Unsigned): VectorType =
    Type(VectorTypeImpl.get(elemType.handle, numElements)).asInstanceOf[VectorType]

  def isValidElementType(elemType: Type): Boolean =
    VectorTypeImpl.isValidElementType(elemType.handle)
}