//
// Created by glavo on 18-3-2.
//

#include <org_glavo_llvm_internal_ir_ArrayTypeImpl.h>
#include <llvm/IR/Type.h>
#include <llvm/IR/DerivedTypes.h>

#include <llvm4s/util.h>

using namespace llvm4s;
using namespace llvm;

jlong Java_org_glavo_llvm_internal_ir_ArrayTypeImpl_get(JNIEnv *, jclass, jlong elemType, jlong numElements) {
    return handleOf(ArrayType::get(get<Type>(elemType), static_cast<uint64_t>(numElements)));
}

jboolean  Java_org_glavo_llvm_internal_ir_ArrayTypeImpl_isValidElementType(JNIEnv *, jclass, jlong elemType) {
    return static_cast<jboolean>(ArrayType::isValidElementType(get<Type>(elemType)));
}
