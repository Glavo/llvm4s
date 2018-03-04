package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;

public class ArrayTypeImpl extends NativeImpl {
    public static native @Handle("ArrayType") long get(@Handle("Type") long elementType, @Unsigned long numElements);

    public static native boolean isValidElementType(@Handle("Type") long elemType);
}
