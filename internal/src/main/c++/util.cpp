//
// Created by glavo on 18-2-26.
//

#include <llvm4s/util.h>
#include <algorithm>
#include <cstring>
#include <iostream>

std::string llvm4s::jbyteArr2str(JNIEnv *env, jbyteArray str) {
    auto bytes = env->GetByteArrayElements(str, nullptr);
    std::string s((const char *)bytes, static_cast<unsigned long>(env->GetArrayLength(str)));
    env->ReleaseByteArrayElements(str, bytes, 0);
    return std::move(s);
}

jbyteArray llvm4s::str2jbyteArr(JNIEnv *env, const std::string &str) {
    jbyteArray arr = env->NewByteArray(static_cast<jsize>(str.size()));
    env->SetByteArrayRegion(
            arr,
            0,
            static_cast<jsize>(str.size()),
            reinterpret_cast<const jbyte *>(str.c_str())
    );

    return arr;
}

std::unordered_map<jlong, std::vector<std::unique_ptr<std::string>>> llvm4s::resources;
std::set<std::vector<llvm::Type *>> llvm4s::typeLists;
