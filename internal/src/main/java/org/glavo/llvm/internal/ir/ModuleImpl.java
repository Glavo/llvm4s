package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.ByteString;
import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;

public final class ModuleImpl extends NativeImpl {

    public static native @Handle("Module") long newInstance(byte[] idStr, long contextHandle);

    public static native void delete(@Handle("Module") long handle);

    public static native @ByteString byte[] getId(@Handle("Module") long handle);

    public static native void setId(@Handle("Module") long handle, @ByteString byte[] idStr);

    public static native @ByteString byte[] getName(@Handle("Module") long handle);

    public static native @ByteString byte[] getSourceFileName(@Handle("Module") long handle);

    public static native void setSourceFileName(@Handle("Module") long handle, @ByteString byte[] idStr);

    public static native @Handle("Context") long getContext(@Handle("Module") long handle);
}
