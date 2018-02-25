package org.glavo.llvm.core

import java.util.Objects

import org.bytedeco.javacpp.LLVM._

case class Value(delegate: LLVMValueRef) {
  Objects.requireNonNull(delegate)

  def kind: Value.Kind = Value.Kind(LLVMGetValueKind(delegate))

  def name: String = LLVMGetValueName(delegate).getString(org.glavo.llvm.LLVMEncoding)

  def name_=(name: String): Unit = {
    LLVMSetValueName(delegate, name)
  }

  def isConstant: Boolean = LLVMIsConstant(delegate) != 0

  def isUndef: Boolean = LLVMIsUndef(delegate) != 0

  override def toString: String = {
    val bs = LLVMPrintValueToString(delegate)
    val s = bs.getString(org.glavo.llvm.LLVMEncoding)
    LLVMDisposeMessage(bs)
    s"Value($s)"
  }
}

object Value {

  sealed abstract class Kind(val id: Int)

  object Kind {

    def apply(id: Int): Kind = id match {
      case LLVMArgumentValueKind => Argument
      case LLVMBasicBlockValueKind => BasicBlock
      case LLVMMemoryUseValueKind => MemoryUse
      case LLVMMemoryDefValueKind => MemoryDef
      case LLVMMemoryPhiValueKind => MemoryPhi
      case LLVMFunctionValueKind => Function
      case LLVMGlobalAliasValueKind => GlobalAlias
      case LLVMGlobalIFuncValueKind => GlobalIFunc
      case LLVMGlobalVariableValueKind => GlobalVariable
      case LLVMBlockAddressValueKind => BlockAddress
      case LLVMConstantExprValueKind => ConstantExpr
      case LLVMConstantArrayValueKind => ConstantArray
      case LLVMConstantStructValueKind => ConstantStruct
      case LLVMConstantVectorValueKind => ConstantVector
      case LLVMUndefValueValueKind => UndefValue
      case LLVMConstantAggregateZeroValueKind => ConstantAggregateZero
      case LLVMConstantDataArrayValueKind => ConstantDataArray
      case LLVMConstantDataVectorValueKind => ConstantDataVector
      case LLVMConstantIntValueKind => ConstantInt
      case LLVMConstantFPValueKind => ConstantFP
      case LLVMConstantPointerNullValueKind => ConstantPointerNull
      case LLVMConstantTokenNoneValueKind => ConstantTokenNone
      case LLVMMetadataAsValueValueKind => MetadataAsValue
      case LLVMInlineAsmValueKind => InlineAsm
      case LLVMInstructionValueKind => Instruction
      case _ => Unknown(id)
    }

    case object Argument extends Kind(LLVMArgumentValueKind)

    case object BasicBlock extends Kind(LLVMBasicBlockValueKind)

    case object MemoryUse extends Kind(LLVMMemoryUseValueKind)

    case object MemoryDef extends Kind(LLVMMemoryDefValueKind)

    case object MemoryPhi extends Kind(LLVMMemoryPhiValueKind)

    case object Function extends Kind(LLVMFunctionValueKind)

    case object GlobalAlias extends Kind(LLVMGlobalAliasValueKind)

    case object GlobalIFunc extends Kind(LLVMGlobalIFuncValueKind)

    case object GlobalVariable extends Kind(LLVMGlobalVariableValueKind)

    case object BlockAddress extends Kind(LLVMBlockAddressValueKind)

    case object ConstantExpr extends Kind(LLVMConstantExprValueKind)

    case object ConstantArray extends Kind(LLVMConstantArrayValueKind)

    case object ConstantStruct extends Kind(LLVMConstantStructValueKind)

    case object ConstantVector extends Kind(LLVMConstantVectorValueKind)

    case object UndefValue extends Kind(LLVMUndefValueValueKind)

    case object ConstantAggregateZero extends Kind(LLVMConstantAggregateZeroValueKind)

    case object ConstantDataArray extends Kind(LLVMConstantDataArrayValueKind)

    case object ConstantDataVector extends Kind(LLVMConstantDataVectorValueKind)

    case object ConstantInt extends Kind(LLVMConstantIntValueKind)

    case object ConstantFP extends Kind(LLVMConstantFPValueKind)

    case object ConstantPointerNull extends Kind(LLVMConstantPointerNullValueKind)

    case object ConstantTokenNone extends Kind(LLVMConstantTokenNoneValueKind)

    case object MetadataAsValue extends Kind(LLVMMetadataAsValueValueKind)

    case object InlineAsm extends Kind(LLVMInlineAsmValueKind)

    case object Instruction extends Kind(LLVMInstructionValueKind)

    case class Unknown(override val id: Int) extends Kind(id)

  }

}
