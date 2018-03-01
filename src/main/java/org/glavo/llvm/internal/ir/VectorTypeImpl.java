package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;
import org.glavo.llvm.ir.Type;
import org.glavo.llvm.ir.VectorType;

public class VectorTypeImpl extends NativeImpl {
    public static native @Handle(VectorType.class) long get(@Handle(Type.class) long elementType, @Unsigned int numElements);

    public static native boolean isValidElementType(@Handle(Type.class) long elemType);

    public static native @Unsigned int getBitWidth(@Handle(VectorType.class) long handle);
}
