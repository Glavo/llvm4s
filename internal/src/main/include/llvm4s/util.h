#pragma once

#include <jni.h>
#include <string>
#include <unordered_map>
#include <set>

#include <llvm/IR/Module.h>
#include <llvm/IR/LLVMContext.h>

namespace llvm4s {
    std::string jbyteArr2str(JNIEnv *, jbyteArray);

    jbyteArray str2jbyteArr(JNIEnv *, const std::string &);

    template<typename T>
    T *get(jlong handle) {
        return reinterpret_cast<T *>(handle);
    }

    template<typename T>
    jlong handleOf(T *ptr) {
        return reinterpret_cast<jlong >(ptr);
    }

    extern std::unordered_map<jlong, std::vector<std::unique_ptr<std::string>>> resources;
    extern std::set<std::vector<llvm::Type *>> typeLists;
}