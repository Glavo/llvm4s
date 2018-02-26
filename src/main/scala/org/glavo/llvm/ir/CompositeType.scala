package org.glavo.llvm.ir

import org.glavo.llvm.Destructor

abstract class CompositeType private[llvm](handle: Long) extends Type(handle) {

}
