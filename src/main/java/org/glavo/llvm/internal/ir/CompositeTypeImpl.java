package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;
import org.glavo.llvm.ir.CompositeType;
import org.glavo.llvm.ir.Type;

public final class CompositeTypeImpl extends NativeImpl {
    //todo: public static native @Handle(Type.class) long getTypeAtIndex(@Handle(CompositeType.class) long handle, @Handle(Value.class) long v);

    public static native @Handle(Type.class) long getTypeAtIndex(@Handle(CompositeType.class) long handle, @Unsigned int idx);

    //todo: public static native boolean indexValid(@Handle(CompositeType.class) int handle, @Handle(Value.class) long v);

    public static native boolean indexValid(@Handle(CompositeType.class) int handle, @Unsigned int idx);
}
