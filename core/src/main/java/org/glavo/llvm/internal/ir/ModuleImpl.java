package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.ByteString;
import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.ir.Context;
import org.glavo.llvm.ir.Module;

public final class ModuleImpl extends NativeImpl {

    public static native @Handle(Module.class) long newInstance(byte[] idStr, long contextHandle);

    public static native void delete(@Handle(Module.class) long handle);

    public static native @ByteString byte[] getId(@Handle(Module.class) long handle);

    public static native void setId(@Handle(Module.class) long handle, @ByteString byte[] idStr);

    public static native @ByteString byte[] getName(@Handle(Module.class) long handle);

    public static native @ByteString byte[] getSourceFileName(@Handle(Module.class) long handle);

    public static native void setSourceFileName(@Handle(Module.class) long handle, @ByteString byte[] idStr);

    public static native @Handle(Context.class) long getContext(@Handle(Module.class) long handle);
}
