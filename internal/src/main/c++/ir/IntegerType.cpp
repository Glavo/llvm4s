//
// Created by glavo on 18-2-27.
//

#include <org_glavo_llvm_internal_ir_IntegerTypeImpl.h>

#include <llvm/IR/Type.h>
#include <llvm/IR/DerivedTypes.h>

#include <llvm4s/util.h>

using namespace llvm4s;

jlong Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_get(JNIEnv *, jclass, jlong contextHandle, jint numBits) {
    return handleOf(llvm::IntegerType::get(*get<llvm::LLVMContext>(contextHandle), static_cast<unsigned int>(numBits)));
}

jint Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_getBitWidth(JNIEnv *, jclass, jlong handle) {
    return get<llvm::IntegerType>(handle)->getBitWidth();
}

jlong Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_getBitMask(JNIEnv *, jclass, jlong handle) {
    return get<llvm::IntegerType>(handle)->getBitMask();
}

jlong Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_getSignBit(JNIEnv *, jclass, jlong handle) {
    return get<llvm::IntegerType>(handle)->getSignBit();
}

jboolean Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_isPowerOf2ByteWidth(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<llvm::IntegerType>(handle)->isPowerOf2ByteWidth());
}

