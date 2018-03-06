//
// Created by glavo on 18-2-27.
//

#include <org_glavo_llvm_internal_ir_StructTypeImpl.h>
#include <llvm/IR/Type.h>

#include <llvm4s/util.h>

using namespace llvm4s;
using namespace llvm;


jlong Java_org_glavo_llvm_internal_ir_StructTypeImpl_create(
        JNIEnv *env,
        jclass,
        jlong contextHandle,
        jlongArray elementTypes,
        jbyteArray name, jboolean isPacked) {
    auto context = get<LLVMContext>(contextHandle);

    auto longs = env->GetLongArrayElements(elementTypes, nullptr);

    std::vector<Type *> tl;
    auto size = env->GetArrayLength(elementTypes);
    for (jsize idx = 0; idx < size; idx++) {
        tl.push_back(get<Type>(longs[idx]));
    }
    env->ReleaseLongArrayElements(elementTypes, longs, 0);

    std::string *n = nullptr;
    if (name) {
        auto p = std::make_unique<std::string>(jbyteArr2str(env, name));
        n = p.get();
        resources[0].push_back(std::move(p));
    }

    return handleOf(StructType::create(
            *context,
            *typeLists.insert(std::move(tl)).first,
            n ? StringRef(*n) : StringRef(),
            isPacked
    ));
}

jlong Java_org_glavo_llvm_internal_ir_StructTypeImpl_get(
        JNIEnv *env, jclass,
        jlong contextHandle,
        jlongArray elementTypes,
        jboolean isPacked) {
    auto context = get<LLVMContext>(contextHandle);

    auto longs = env->GetLongArrayElements(elementTypes, nullptr);

    std::vector<Type *> tl;
    auto size = env->GetArrayLength(elementTypes);
    for (jsize idx = 0; idx < size; idx++) {
        tl.push_back(get<Type>(longs[idx]));
    }
    env->ReleaseLongArrayElements(elementTypes, longs, 0);

    return handleOf(StructType::get(
            *context,
            *typeLists.insert(std::move(tl)).first,
            isPacked
    ));
}


jboolean Java_org_glavo_llvm_internal_ir_StructTypeImpl_isValidElementType(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(StructType::isValidElementType(get<Type>(handle)));
}


jboolean Java_org_glavo_llvm_internal_ir_StructTypeImpl_isPacked(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<StructType>(handle)->isPacked());
}


jboolean Java_org_glavo_llvm_internal_ir_StructTypeImpl_isLiteral(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<StructType>(handle)->isLiteral());
}


jboolean Java_org_glavo_llvm_internal_ir_StructTypeImpl_isQpaque(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<StructType>(handle)->isOpaque());
}


jboolean Java_org_glavo_llvm_internal_ir_StructTypeImpl_hasName(JNIEnv *, jclass, jlong handle) {
    return static_cast<jboolean>(get<StructType>(handle)->hasName());
}


jbyteArray Java_org_glavo_llvm_internal_ir_StructTypeImpl_getName(JNIEnv *env, jclass, jlong handle) {
    return str2jbyteArr(env, get<StructType>(handle)->getName().str());
}


void Java_org_glavo_llvm_internal_ir_StructTypeImpl_setName(JNIEnv *env, jclass, jlong handle, jbyteArray name) {
    if (!name)
        return;
    auto p = std::make_unique<std::string>(jbyteArr2str(env, name));
    get<StructType>(handle)->setName(*p);
    resources[0].push_back(std::move(p));
}


jlongArray Java_org_glavo_llvm_internal_ir_StructTypeImpl_getElements(JNIEnv *env, jclass, jlong handle) {
    auto ps = get<StructType>(handle)->elements();
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


jint Java_org_glavo_llvm_internal_ir_StructTypeImpl_getNumElements(JNIEnv *, jclass, jlong handle) {
    return get<StructType>(handle)->getNumElements();
}


jlong Java_org_glavo_llvm_internal_ir_StructTypeImpl_getElementType(JNIEnv *, jclass, jlong handle, jint idx) {
    return handleOf(get<StructType>(handle)->getElementType(static_cast<unsigned int>(idx)));
}


void Java_org_glavo_llvm_internal_ir_StructTypeImpl_setBody(JNIEnv *env, jclass, jlong handle, jlongArray elements,
                                                            jboolean isPacked) {
    auto longs = env->GetLongArrayElements(elements, nullptr);

    std::vector<Type *> tl;
    auto size = env->GetArrayLength(elements);
    for (jsize idx = 0; idx < size; idx++) {
        tl.push_back(get<Type>(longs[idx]));
    }
    env->ReleaseLongArrayElements(elements, longs, 0);

    get<StructType>(handle)->setBody(*typeLists.insert(std::move(tl)).first, isPacked);
}