//
// Created by glavo on 18-2-27.
//
#include <org_glavo_llvm_internal_ir_FunctionTypeImpl.h>

#include <llvm/IR/Type.h>
#include <llvm/IR/DerivedTypes.h>

#include <llvm4s/util.h>

using namespace llvm4s;
using namespace llvm;

jlong Java_org_glavo_llvm_internal_ir_FunctionTypeImpl_get(JNIEnv *env, jclass, jlong retTypeHandle, jlongArray parTypeHandles,
                                                  jboolean isVarargs) {
    auto ret = get<llvm::Type>(retTypeHandle);
    if (parTypeHandles == nullptr || env->GetArrayLength(parTypeHandles) == 0) {
        return handleOf(FunctionType::get(ret, ArrayRef<Type *>(), isVarargs));
    }

    auto longs = env->GetLongArrayElements(parTypeHandles, nullptr);

    std::vector<Type *> tl;
    auto size = env->GetArrayLength(parTypeHandles);
    for (jsize idx = 0; idx < size; idx++) {
        tl.push_back(get<Type>(longs[idx]));
    }
    env->ReleaseLongArrayElements(parTypeHandles, longs, 0);

    auto &v = *typeLists.insert(std::move(tl)).first;
    return handleOf(FunctionType::get(ret, v, isVarargs));
}

jboolean Java_org_glavo_llvm_internal_ir_FunctionTypeImpl_isValidReturnType(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(FunctionType::isValidReturnType(get<Type>(handle)));
}

jboolean Java_org_glavo_llvm_internal_ir_FunctionTypeImpl_isValidArgumentType(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(FunctionType::isValidArgumentType(get<Type>(handle)));
}

jboolean Java_org_glavo_llvm_internal_ir_FunctionTypeImpl_isVarArg(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<FunctionType>(handle)->isVarArg());
}

jlong Java_org_glavo_llvm_internal_ir_FunctionTypeImpl_getReturnType(JNIEnv *, jclass, jlong handle) {
    return handleOf(get<FunctionType>(handle)->getReturnType());
}

jlongArray Java_org_glavo_llvm_internal_ir_FunctionTypeImpl_getParamTypes(JNIEnv *env, jclass, jlong handle) {
    auto ps = get<FunctionType>(handle)->params();
    if (ps.empty()) {
        return env->NewLongArray(0);
    }

    auto arr = env->NewLongArray(static_cast<jsize>(ps.size()));
    auto longArr = new jlong[ps.size()];
    for (auto idx = 0; idx < ps.size(); idx++) {
        longArr[idx] = handleOf(ps[idx]);
    }
    env->SetLongArrayRegion(arr, 0, static_cast<jsize>(ps.size()), longArr);
    return arr;
}

