//
// Created by glavo on 18-2-27.
//

#include <org_glavo_llvm_internal_ir_CompositeTypeImpl.h>
#include <llvm/IR/Type.h>

#include <llvm4s/util.h>

using namespace llvm4s;
using namespace llvm;

jlong Java_org_glavo_llvm_internal_ir_CompositeTypeImpl_getTypeAtIndex(JNIEnv *, jclass, jlong handle, jint idx) {
    return handleOf(get<CompositeType>(handle)->getTypeAtIndex(static_cast<unsigned int>(idx)));
}

jboolean Java_org_glavo_llvm_internal_ir_CompositeTypeImpl_indexValid(JNIEnv *, jclass, jlong handle, jint idx) {
    return static_cast<jboolean>(get<CompositeType>(handle)->indexValid(static_cast<unsigned int>(idx)));
}

