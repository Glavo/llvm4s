package org.glavo.llvm.ir;

import org.glavo.llvm.Handle;
import org.glavo.llvm.NativeImpl;
import org.glavo.llvm.Unsigned;

public final class PointerTypeImpl extends NativeImpl {
    public static native @Handle(PointerType.class) long get(@Handle(Type.class) long elementType, @Unsigned int addressSpace);

    public static native boolean isValidElementType(@Handle(Type.class) long elemType);

    public static native boolean isLoadableOrStorableType(@Handle(Type.class) long elemType);

    public static native @Handle(Type.class) long getElementType(@Handle(PointerType.class) long handle);

    public static native @Unsigned int getAdressSpace(@Handle(PointerType.class) long handle);
}
