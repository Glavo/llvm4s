//
// Created by glavo on 18-3-2.
//
#include <org_glavo_llvm_internal_ir_VectorTypeImpl.h>

#include <llvm/IR/Type.h>
#include <llvm/IR/DerivedTypes.h>

#include <llvm4s/util.h>

using namespace llvm4s;
using namespace llvm;

jlong Java_org_glavo_llvm_internal_ir_VectorTypeImpl_get(JNIEnv *, jclass, jlong elemType, jint numElements) {
    return handleOf(VectorType::get(get<Type>(elemType), static_cast<unsigned int>(numElements)));
}

jboolean Java_org_glavo_llvm_internal_ir_VectorTypeImpl_isValidElementType(JNIEnv *, jclass, jlong elemType) {
    return static_cast<jboolean>(VectorType::isValidElementType(get<Type>(elemType)));
}

jint Java_org_glavo_llvm_internal_ir_VectorTypeImpl_getBitWidth(JNIEnv *, jclass, jlong handle) {
    return get<VectorType>(handle)->getBitWidth();
}