//
// Created by glavo on 18-2-27.
//

#include <org_glavo_llvm_internal_ir_PointerTypeImpl.h>
#include <llvm/IR/Type.h>
#include <llvm/Support/raw_ostream.h>

#include <llvm4s/util.h>

using namespace llvm4s;
using namespace llvm;

jlong Java_org_glavo_llvm_internal_ir_PointerTypeImpl_get(JNIEnv *, jclass, jlong elemTypeHandle, jint addressSpace) {
    return handleOf(PointerType::get(get<Type>(elemTypeHandle), static_cast<unsigned int>(addressSpace)));
}


jboolean Java_org_glavo_llvm_internal_ir_PointerTypeImpl_isValidElementType(JNIEnv *, jclass, jlong elemType) {
    return static_cast<jboolean>(PointerType::isValidElementType(get<Type>(elemType)));
}


jboolean Java_org_glavo_llvm_internal_ir_PointerTypeImpl_isLoadableOrStorableType(JNIEnv *, jclass, jlong elemType) {
    return static_cast<jboolean>(PointerType::isLoadableOrStorableType(get<Type>(elemType)));
}


jlong Java_org_glavo_llvm_internal_ir_PointerTypeImpl_getElementType(JNIEnv *, jclass, jlong handle) {
    return handleOf(get<PointerType>(handle)->getElementType());
}

jint Java_org_glavo_llvm_internal_ir_PointerTypeImpl_getAddressSpace(JNIEnv *, jclass, jlong handle) {
    return get<PointerType>(handle)->getAddressSpace();
}