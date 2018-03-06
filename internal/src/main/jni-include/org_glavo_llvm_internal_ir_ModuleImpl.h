/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_glavo_llvm_internal_ir_ModuleImpl */

#ifndef _Included_org_glavo_llvm_internal_ir_ModuleImpl
#define _Included_org_glavo_llvm_internal_ir_ModuleImpl
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_glavo_llvm_internal_ir_ModuleImpl
 * Method:    newInstance
 * Signature: ([BJ)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_ModuleImpl_newInstance
  (JNIEnv *, jclass, jbyteArray, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ModuleImpl
 * Method:    delete
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_ModuleImpl_delete
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ModuleImpl
 * Method:    getId
 * Signature: (J)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_glavo_llvm_internal_ir_ModuleImpl_getId
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ModuleImpl
 * Method:    setId
 * Signature: (J[B)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_ModuleImpl_setId
  (JNIEnv *, jclass, jlong, jbyteArray);

/*
 * Class:     org_glavo_llvm_internal_ir_ModuleImpl
 * Method:    getName
 * Signature: (J)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_glavo_llvm_internal_ir_ModuleImpl_getName
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ModuleImpl
 * Method:    getSourceFileName
 * Signature: (J)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_glavo_llvm_internal_ir_ModuleImpl_getSourceFileName
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ModuleImpl
 * Method:    setSourceFileName
 * Signature: (J[B)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_ModuleImpl_setSourceFileName
  (JNIEnv *, jclass, jlong, jbyteArray);

/*
 * Class:     org_glavo_llvm_internal_ir_ModuleImpl
 * Method:    getContext
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_ModuleImpl_getContext
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif