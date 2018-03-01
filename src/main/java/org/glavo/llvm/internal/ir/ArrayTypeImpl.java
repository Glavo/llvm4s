package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;
import org.glavo.llvm.ir.ArrayType;
import org.glavo.llvm.ir.Type;

public class ArrayTypeImpl extends NativeImpl {
    public static native @Handle(ArrayType.class) long get(@Handle(Type.class) long elementType, @Unsigned long numElements);

    public static native boolean isValidElementType(@Handle(Type.class) long elemType);
}
