package org.glavo

import scala.reflect.ClassTag


package object llvm {
  val LLVMEncoding: String = "UTF-8"

  type Destructor[-T] = T => Unit
}
