package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;

public class VectorTypeImpl extends NativeImpl {
    public static native @Handle("VectorType") long get(@Handle("Type") long elementType, @Unsigned int numElements);

    public static native boolean isValidElementType(@Handle("Type") long elemType);

    public static native @Unsigned int getBitWidth(@Handle("VectorType") long handle);
}
