/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_glavo_llvm_internal_ir_ValueImpl */

#ifndef _Included_org_glavo_llvm_internal_ir_ValueImpl
#define _Included_org_glavo_llvm_internal_ir_ValueImpl
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_glavo_llvm_internal_ir_ValueImpl
 * Method:    delete
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_ValueImpl_delete
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ValueImpl
 * Method:    getType
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_ValueImpl_getType
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ValueImpl
 * Method:    getContext
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_ValueImpl_getContext
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ValueImpl
 * Method:    hasName
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_glavo_llvm_internal_ir_ValueImpl_hasName
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ValueImpl
 * Method:    getName
 * Signature: (J)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_glavo_llvm_internal_ir_ValueImpl_getName
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ValueImpl
 * Method:    setName
 * Signature: (J[B)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_ValueImpl_setName
  (JNIEnv *, jclass, jlong, jbyteArray);

/*
 * Class:     org_glavo_llvm_internal_ir_ValueImpl
 * Method:    takeName
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_ValueImpl_takeName
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_ValueImpl
 * Method:    replaceAllUsesWith
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_ValueImpl_replaceAllUsesWith
  (JNIEnv *, jclass, jlong, jlong);

#ifdef __cplusplus
}
#endif
#endif
