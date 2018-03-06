/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_glavo_llvm_internal_ir_IntegerTypeImpl */

#ifndef _Included_org_glavo_llvm_internal_ir_IntegerTypeImpl
#define _Included_org_glavo_llvm_internal_ir_IntegerTypeImpl
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_glavo_llvm_internal_ir_IntegerTypeImpl
 * Method:    get
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_get
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     org_glavo_llvm_internal_ir_IntegerTypeImpl
 * Method:    getBitWidth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_getBitWidth
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_IntegerTypeImpl
 * Method:    getBitMask
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_getBitMask
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_IntegerTypeImpl
 * Method:    getSignBit
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_getSignBit
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_IntegerTypeImpl
 * Method:    isPowerOf2ByteWidth
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_glavo_llvm_internal_ir_IntegerTypeImpl_isPowerOf2ByteWidth
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
