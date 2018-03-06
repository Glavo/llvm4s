//
// Created by glavo on 18-2-26.
//

#include <org_glavo_llvm_internal_ir_ModuleImpl.h>

#include <llvm/IR/Module.h>
#include <llvm-c/Core.h>

#include <map>
#include <string>
#include <vector>

#include <llvm4s/util.h>

using namespace llvm4s;

jlong
Java_org_glavo_llvm_internal_ir_ModuleImpl_newInstance(JNIEnv *env, jclass, jbyteArray idStr, jlong contextHandle) {
    auto str = std::make_unique<std::string>(jbyteArr2str(env, idStr));
    auto h = (jlong) new llvm::Module(*str, *get<llvm::LLVMContext>(contextHandle));
    resources[h].push_back(std::move(str));
    return (long) h;
}

void Java_org_glavo_llvm_internal_ir_ModuleImpl_delete(JNIEnv *, jclass, jlong handle) {
    if (handle)
        resources.erase(handle);
    delete get<llvm::Module>(handle);
}

jbyteArray Java_org_glavo_llvm_internal_ir_ModuleImpl_getId(JNIEnv *env, jclass, jlong handle) {
    return str2jbyteArr(env, get<llvm::Module>(handle)->getModuleIdentifier());
}

void Java_org_glavo_llvm_internal_ir_ModuleImpl_setId(JNIEnv *env, jclass, jlong handle, jbyteArray idStr) {
    auto str = std::make_unique<std::string>(jbyteArr2str(env, idStr));
    get<llvm::Module>(handle)->setModuleIdentifier(*str);
    resources[handle].push_back(std::move(str));
}

jbyteArray Java_org_glavo_llvm_internal_ir_ModuleImpl_getName(JNIEnv *env, jclass, jlong handle) {
    return str2jbyteArr(env, get<llvm::Module>(handle)->getName().str());
}

void
Java_org_glavo_llvm_internal_ir_ModuleImpl_setSourceFileName(JNIEnv *env, jclass, jlong handle, jbyteArray nameStr) {
    auto str = std::make_unique<std::string>(jbyteArr2str(env, nameStr));
    get<llvm::Module>(handle)->setSourceFileName(*str);
    resources[handle].push_back(std::move(str));
}

jbyteArray Java_org_glavo_llvm_internal_ir_ModuleImpl_getSourceFileName(JNIEnv *env, jclass, jlong handle) {
    return str2jbyteArr(env, get<llvm::Module>(handle)->getName().str());
}

jlong Java_org_glavo_llvm_internal_ir_ModuleImpl_getContext(JNIEnv *, jclass, jlong handle) {
    return reinterpret_cast<jlong>(&get<llvm::Module>(handle)->getContext());
}
