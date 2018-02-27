package org.glavo.llvm.ir

import java.util.Objects

import org.glavo.llvm.internal.JNIUtils
import org.glavo.llvm.internal.ir.StructTypeImpl

class StructType private[llvm](handle: Long) extends CompositeType(handle) {
  def isPacked: Boolean = StructTypeImpl.isPacked(handle)

  def isLiteral: Boolean = StructTypeImpl.isLiteral(handle)

  def isQpaque: Boolean = StructTypeImpl.isQpaque(handle)

  def hasName: Boolean = StructTypeImpl.hasName(handle)

  def name: String = Option(StructTypeImpl.getName(handle)).map(JNIUtils.bytes2str).orNull

  def name_=(name: String): Unit = {
    Objects.requireNonNull(name)
    StructTypeImpl.setName(handle, JNIUtils.str2bytes(name))
  }

  def elements: Seq[Type] = StructTypeImpl.getElements(handle).map(Type.apply)

  def elements_=(elems: Seq[Type]): Unit = {
    StructTypeImpl.setBody(handle, elems.map(_.handle).toArray, isPacked)
  }

  def setBody(elems: Seq[Type], isPacked: Boolean = this.isPacked): Unit = {
    StructTypeImpl.setBody(handle, elems.map(_.handle).toArray, isPacked)
  }

  def size: Int = StructTypeImpl.getNumElements(handle)
}

object StructType {
  def create(name: String = null, context: Context = null, isPacked: Boolean = false)(elements: Type*): StructType = {
    val ls = elements.map(_.handle).toArray

    if (context == null && elements.isEmpty) {
      throw new IllegalArgumentException("elements is empty")
    }

    val c = Option(context).map(_.handle).getOrElse(elements.head.context.handle)

    Type(StructTypeImpl.create(
      c,
      ls,
      if (name == null) null else JNIUtils.str2bytes(name),
      isPacked
    )).asInstanceOf[StructType]
  }

  def get(context: Context = null, isPacked: Boolean = false)(elements: Type*): StructType = {
    val ls = elements.map(_.handle).toArray

    if (context == null && elements.isEmpty) {
      throw new IllegalArgumentException("elements is empty")
    }

    val c = Option(context).map(_.handle).getOrElse(elements.head.context.handle)

    Type(StructTypeImpl.get(
      c,
      ls,
      isPacked
    )).asInstanceOf[StructType]
  }

  def isValidElementType(tpe: Type): Boolean = StructTypeImpl.isValidElementType(tpe.handle)
}
