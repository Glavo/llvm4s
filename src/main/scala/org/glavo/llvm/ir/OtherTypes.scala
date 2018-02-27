package org.glavo.llvm.ir

import org.glavo.llvm.internal.ir.TypeImpl

class LabelType private[llvm](handle: Long) extends Type(handle)

object LabelType {
  def apply()(implicit context: Context): LabelType =
    Type(TypeImpl.getLabel(context.handle)).asInstanceOf[LabelType]
}

class MetadataType private[llvm](handle: Long) extends Type(handle)

object MetadataType {
  def apply()(implicit context: Context): MetadataType =
    Type(TypeImpl.getMetadataTy(context.handle)).asInstanceOf[MetadataType]
}

class X86_MMXType private[llvm](handle: Long) extends Type(handle)

object X86_MMXType {
  def apply()(implicit context: Context): X86_MMXType =
    Type(TypeImpl.getX86_MMXTy(context.handle)).asInstanceOf[X86_MMXType]
}

class TokenType private[llvm](handle: Long) extends Type(handle)

object TokenType {
  def apply()(implicit context: Context): TokenType =
    Type(TypeImpl.getTokenTy(context.handle)).asInstanceOf[TokenType]
}
