package org.glavo.llvm

import java.util.Objects

import org.bytedeco.javacpp.LLVM

case class Value(delegate: LLVM.LLVMValueRef) {
  Objects.requireNonNull(delegate)


}

object Value {

  sealed abstract class Kind(val id: Int)

  object Kind {

    case object Argument extends Kind(LLVM.LLVMArgumentValueKind)

    case object BasicBlock extends Kind(LLVM.LLVMBasicBlockValueKind)

    case object MemoryUse extends Kind(LLVM.LLVMMemoryUseValueKind)

    case object MemoryDef extends Kind(LLVM.LLVMMemoryDefValueKind)

    case object MemoryPhi extends Kind(LLVM.LLVMMemoryPhiValueKind)

    case object Function extends Kind(LLVM.LLVMFunctionValueKind)

    case object GlobalAlias extends Kind(LLVM.LLVMGlobalAliasValueKind)

    case object GlobalFunc extends Kind(LLVM.LLVMGlobalIFuncValueKind)

    case object GlobalVariable extends Kind(LLVM.LLVMGlobalVariableValueKind)

    case object BlockAddress extends Kind(LLVM.LLVMBlockAddressValueKind)

    case object ConstantExpr extends Kind(LLVM.LLVMConstantExprValueKind)

    case object ConstantArray extends Kind(LLVM.LLVMConstantArrayValueKind)

    case object ConstantStruct extends Kind(LLVM.LLVMConstantStructValueKind)

    case object ConstantVector extends Kind(LLVM.LLVMConstantVectorValueKind)

    case object UndefValue extends Kind(LLVM.LLVMUndefValueValueKind)

    case object ConstantAggregateZero extends Kind(LLVM.LLVMConstantAggregateZeroValueKind)

    case object ConstantDataArray extends Kind(LLVM.LLVMConstantDataArrayValueKind)

    case object ConstantDataVector extends Kind(LLVM.LLVMConstantDataVectorValueKind)

    case object ConstantInt extends Kind(LLVM.LLVMConstantIntValueKind)

    case object ConstantFP extends Kind(LLVM.LLVMConstantFPValueKind)

    case object ConstantPointer extends Kind(LLVM.LLVMConstantPointerNullValueKind)

    case object ConstantTokenNone extends Kind(LLVM.LLVMConstantTokenNoneValueKind)

    case object MetadataAsValue extends Kind(LLVM.LLVMMetadataAsValueValueKind)

    case object InlineAsm extends Kind(LLVM.LLVMInlineAsmValueKind)

    case object Instruction extends Kind(LLVM.LLVMInstructionValueKind)

    case class Unknown(override val id: Int) extends Kind(id)

  }

}
