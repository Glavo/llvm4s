package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.ByteString;
import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;

public class ValueImpl extends NativeImpl {
    public static native void delete(@Handle("Value") long handle);

    public static native @Handle("Type") long getType(@Handle("Value") long handle);

    public static native @Handle("Context") long getContext(@Handle("Value") long handle);

    public static native boolean hasName(@Handle("Value") long handle);

    public static native @ByteString byte[] getName(@Handle("Value") long handle);

    public static native void setName(@Handle("Value") long handle, @ByteString byte[] name);

    public static native void takeName(@Handle("Value") long handle, @Handle("Value") long v);

    public static native void replaceAllUsesWith(@Handle("Value") long handle, @Handle("Value") long v);
}
