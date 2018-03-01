package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;
import org.glavo.llvm.ir.Type;

public class SequentialTypeImpl extends NativeImpl {
    public static native @Unsigned long getNumElements(@Handle(SequentialTypeImpl.class) long handle);

    public static native @Handle(Type.class) long getElementType(@Handle(SequentialTypeImpl.class) long handle);
}
