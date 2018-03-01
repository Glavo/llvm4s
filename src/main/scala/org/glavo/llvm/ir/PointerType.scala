package org.glavo.llvm.ir

import org.glavo.llvm.internal.Unsigned
import org.glavo.llvm.internal.ir.PointerTypeImpl

class PointerType private[llvm](handle: Long) extends Type(handle) {
  val elementType: Type = Type(PointerTypeImpl.getElementType(handle))

  val addressSpace: Int@Unsigned = PointerTypeImpl.getAddressSpace(handle)

  override def toString: String = s"PointerType($mkString)"
}

object PointerType {
  def apply(elemType: Type, addressSpace: Int@Unsigned = 0): PointerType =
    Type(PointerTypeImpl.get(elemType.handle, addressSpace)).asInstanceOf[PointerType]

  def isValidElementType(tpe: Type): Boolean = PointerTypeImpl.isValidElementType(tpe.handle)

  def isLoadableOrStorableType(tpe: Type): Boolean = PointerTypeImpl.isLoadableOrStorableType(tpe.handle)
}