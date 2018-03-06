//
// Created by glavo on 18-2-26.
//

#include <llvm4s/util.h>
#include <org_glavo_llvm_internal_ir_ContextImpl.h>

#include <llvm/IR/LLVMContext.h>
#include <llvm-c/Core.h>

using namespace llvm4s;

jlong Java_org_glavo_llvm_internal_ir_ContextImpl_newInstance(JNIEnv *, jclass) {
    return handleOf(new llvm::LLVMContext());
}

void Java_org_glavo_llvm_internal_ir_ContextImpl_delete(JNIEnv *, jclass, jlong handle) {
    delete get<llvm::LLVMContext>(handle);
}

jlong Java_org_glavo_llvm_internal_ir_ContextImpl_getGlobal(JNIEnv *, jclass) {
    return handleOf(llvm::unwrap(LLVMGetGlobalContext()));
}

