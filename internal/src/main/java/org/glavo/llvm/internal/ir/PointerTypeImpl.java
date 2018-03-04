package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;

public final class PointerTypeImpl extends NativeImpl {
    public static native @Handle("PointerType") long get(@Handle("Type") long elementType, @Unsigned int addressSpace);

    public static native boolean isValidElementType(@Handle("Type") long elemType);

    public static native boolean isLoadableOrStorableType(@Handle("Type") long elemType);

    public static native @Handle("Type") long getElementType(@Handle("PointerType") long handle);

    public static native @Unsigned int getAddressSpace(@Handle("PointerType") long handle);
}
