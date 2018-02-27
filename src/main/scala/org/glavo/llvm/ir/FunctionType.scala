package org.glavo.llvm.ir

import java.util.Objects

import org.glavo.llvm.internal.ir.FunctionTypeImpl

class FunctionType private[llvm](handle: Long) extends Type(handle) {
  val isVarargs: Boolean = FunctionTypeImpl.isVarArg(handle)

  val returnType: Type = Type(FunctionTypeImpl.getRetunrnType(handle))

  lazy val parameterTypes: Seq[Type] = FunctionTypeImpl.getParamTypes(handle).map(Type.apply)
}

object FunctionType {
  def apply(returnType: Type, parameterTypes: Seq[Type], isVarargs: Boolean = false): FunctionType = {
    Objects.requireNonNull(returnType)
    val args = parameterTypes.view.map(_.handle).toArray
    val r = FunctionTypeImpl.get(returnType.handle, args, isVarargs)

    Type(r).asInstanceOf[FunctionType]
  }

  def isValidReturnType(tpe: Type): Boolean =
    FunctionTypeImpl.isValidReturnType(tpe.handle)


  def isValidArgumentType(tpe: Type): Boolean =
    FunctionTypeImpl.isValidArgumentType(tpe.handle)
}