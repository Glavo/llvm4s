//
// Created by glavo on 18-2-27.
//

#include <org_glavo_llvm_internal_ir_TypeImpl.h>

#include <llvm/IR/Type.h>
#include <llvm/Support/raw_ostream.h>

#include <llvm4s/util.h>

using namespace llvm4s;
using namespace llvm;


jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getVoidTy(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getVoidTy(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getLabel(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getLabelTy(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getHalfTy(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getHalfTy(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getFloatTy(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getFloatTy(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getDoubleTy(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getDoubleTy(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getMetadataTy(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getMetadataTy(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getX86_1FP80Ty(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getX86_FP80Ty(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getFP128Ty(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getFP128Ty(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getPPC_1FP128Ty(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getPPC_FP128Ty(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getX86_1MMXTy(JNIEnv *, jclass, jlong context){
    return handleOf(Type::getX86_MMXTy(*get<LLVMContext>(context)));
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getTokenTy(JNIEnv *, jclass, jlong context) {
    return handleOf(Type::getTokenTy(*get<LLVMContext>(context)));
}


jbyteArray Java_org_glavo_llvm_internal_ir_TypeImpl_toString(JNIEnv *env, jclass, jlong handle) {
    std::string str;
    llvm::raw_string_ostream stream(str);
    get<llvm::Type>(handle)->print(stream);
    stream.flush();
    return str2jbyteArr(env, str);
}

jlong Java_org_glavo_llvm_internal_ir_TypeImpl_getContext(JNIEnv *, jclass, jlong handle) {
    return handleOf(&get<llvm::Type>(handle)->getContext());
}

jint Java_org_glavo_llvm_internal_ir_TypeImpl_getTypeId(JNIEnv *, jclass, jlong handle) {
    return get<llvm::Type>(handle)->getTypeID();
}

jboolean Java_org_glavo_llvm_internal_ir_TypeImpl_isFPOrFPVectorTy(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<llvm::Type>(handle)->isFPOrFPVectorTy());
}

jboolean Java_org_glavo_llvm_internal_ir_TypeImpl_isIntegerTy(JNIEnv *, jclass, jlong handle, jint bitWidth) {
    return static_cast<jboolean>(get<llvm::Type>(handle)->isIntegerTy(static_cast<unsigned int>(bitWidth)));
}


jboolean Java_org_glavo_llvm_internal_ir_TypeImpl_isIntOrIntVectorTy__J(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<llvm::Type>(handle)->isIntOrIntVectorTy());
}

jboolean Java_org_glavo_llvm_internal_ir_TypeImpl_isIntOrIntVectorTy__JI(JNIEnv *, jclass, jlong handle, jint bitWidth) {
    return static_cast<jboolean>(get<llvm::Type>(handle)->isIntOrIntVectorTy(static_cast<unsigned int>(bitWidth)));
}

jboolean Java_org_glavo_llvm_internal_ir_TypeImpl_isPtrOrPtrVectorTy(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<llvm::Type>(handle)->isPtrOrPtrVectorTy());
}

jboolean Java_org_glavo_llvm_internal_ir_TypeImpl_canLosslesslyBitCastTo(JNIEnv *, jclass, jlong handle1, jlong handle2) {
    return static_cast<jboolean>(get<llvm::Type>(handle1)->canLosslesslyBitCastTo(get<llvm::Type>(handle2)));
}

jboolean Java_org_glavo_llvm_internal_ir_TypeImpl_isEmptyTy(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<llvm::Type>(handle)->isEmptyTy());
}

jint Java_org_glavo_llvm_internal_ir_TypeImpl_getPrimitiveSizeInBits(JNIEnv *, jclass, jlong handle) {
    return get<llvm::Type>(handle)->getPrimitiveSizeInBits();
}

jint Java_org_glavo_llvm_internal_ir_TypeImpl_getScalarSizeInBits(JNIEnv *, jclass, jlong handle) {
    return get<llvm::Type>(handle)->getScalarSizeInBits();
}

jint Java_org_glavo_llvm_internal_ir_TypeImpl_getFPMantissaWidth(JNIEnv *, jclass, jlong handle) {
    return get<llvm::Type>(handle)->getFPMantissaWidth();
}