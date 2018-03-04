package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;

public class SequentialTypeImpl extends NativeImpl {
    public static native @Unsigned long getNumElements(@Handle("SequentialTypeImpl") long handle);

    public static native @Handle("Type") long getElementType(@Handle("SequentialTypeImpl") long handle);
}
