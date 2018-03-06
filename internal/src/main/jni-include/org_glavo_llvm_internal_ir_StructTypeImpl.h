/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_glavo_llvm_internal_ir_StructTypeImpl */

#ifndef _Included_org_glavo_llvm_internal_ir_StructTypeImpl
#define _Included_org_glavo_llvm_internal_ir_StructTypeImpl
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    create
 * Signature: (J[J[BZ)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_create
  (JNIEnv *, jclass, jlong, jlongArray, jbyteArray, jboolean);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    get
 * Signature: (J[JZ)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_get
  (JNIEnv *, jclass, jlong, jlongArray, jboolean);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    isValidElementType
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_isValidElementType
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    isPacked
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_isPacked
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    isLiteral
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_isLiteral
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    isQpaque
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_isQpaque
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    hasName
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_hasName
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    getName
 * Signature: (J)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_getName
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    setName
 * Signature: (J[B)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_setName
  (JNIEnv *, jclass, jlong, jbyteArray);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    getElements
 * Signature: (J)[J
 */
JNIEXPORT jlongArray JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_getElements
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    getNumElements
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_getNumElements
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    getElementType
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_getElementType
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     org_glavo_llvm_internal_ir_StructTypeImpl
 * Method:    setBody
 * Signature: (J[JZ)V
 */
JNIEXPORT void JNICALL Java_org_glavo_llvm_internal_ir_StructTypeImpl_setBody
  (JNIEnv *, jclass, jlong, jlongArray, jboolean);

#ifdef __cplusplus
}
#endif
#endif