package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;

public final class CompositeTypeImpl extends NativeImpl {
    //todo: public static native @Handle("Type") long getTypeAtIndex(@Handle("CompositeType") long handle, @Handle(Value.class) long v);

    public static native @Handle("Type") long getTypeAtIndex(@Handle("CompositeType") long handle, @Unsigned int idx);

    //todo: public static native boolean indexValid(@Handle("CompositeType") int handle, @Handle(Value.class) long v);

    public static native boolean indexValid(@Handle("CompositeType") long handle, @Unsigned int idx);
}
