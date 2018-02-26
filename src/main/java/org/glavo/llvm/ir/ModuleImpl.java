package org.glavo.llvm.ir;

import org.glavo.llvm.ByteString;
import org.glavo.llvm.Handle;
import org.glavo.llvm.JNIUtils;

import java.nio.ByteBuffer;

public final class ModuleImpl {
    static {
        JNIUtils.load();
    }

    public static native @Handle(Module.class) long newInstance(byte[] idStr, long contextHandle);

    public static native void delete(@Handle(Module.class) long handle);

    public static native @ByteString byte[] getId(@Handle(Module.class) long handle);

    public static native void setId(@Handle(Module.class) long handle, @ByteString byte[] idStr);

    public static native @ByteString byte[] getName(@Handle(Module.class) long handle);

    public static native @ByteString byte[] getSourceFileName(@Handle(Module.class) long handle);

    public static native void setSourceFileName(@Handle(Module.class) long handle, @ByteString byte[] idStr);
}
