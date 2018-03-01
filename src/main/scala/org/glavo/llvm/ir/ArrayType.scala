package org.glavo.llvm.ir

import org.glavo.llvm.internal.Unsigned
import org.glavo.llvm.internal.ir.ArrayTypeImpl

class ArrayType private[llvm](handle: Long) extends SequentialType(handle) {
  override def toString: String = s"ArrayType($mkString)"
}

object ArrayType {

  def apply(elemType: Type, numElements: Int@Unsigned): ArrayType =
    Type(ArrayTypeImpl.get(elemType.handle, numElements)).asInstanceOf[ArrayType]

  def isValidElementType(elemType: Type): Boolean =
    ArrayTypeImpl.isValidElementType(elemType.handle)
}