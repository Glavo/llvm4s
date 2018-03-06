//
// Created by glavo on 18-3-1.
//

#include <org_glavo_llvm_internal_ir_SequentialTypeImpl.h>

#include <llvm/IR/Type.h>
#include <llvm/IR/DerivedTypes.h>

#include <llvm4s/util.h>

using namespace llvm4s;
using namespace llvm;

jlong Java_org_glavo_llvm_internal_ir_SequentialTypeImpl_getNumElements(JNIEnv *, jclass, jlong handle) {
    return get<SequentialType>(handle)->getNumElements();
}

jlong Java_org_glavo_llvm_internal_ir_SequentialTypeImpl_getElementType(JNIEnv *, jclass, jlong handle) {
    return handleOf(get<SequentialType>(handle)->getElementType());
}