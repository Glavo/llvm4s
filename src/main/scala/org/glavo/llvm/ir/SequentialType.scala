package org.glavo.llvm.ir

import org.glavo.llvm.internal.Unsigned
import org.glavo.llvm.internal.ir.SequentialTypeImpl

abstract class SequentialType private[llvm](handle: Long) extends CompositeType(handle) {
  val numElements: Long@Unsigned = SequentialTypeImpl.getNumElements(handle)

  val elementType: Type = Type(SequentialTypeImpl.getElementType(handle))
}
