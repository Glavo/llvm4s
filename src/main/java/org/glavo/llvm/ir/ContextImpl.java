package org.glavo.llvm.ir;

import org.glavo.llvm.NativeImpl;

public final class ContextImpl extends NativeImpl{

    public static native long newInstance();

    public static native void delete(long handle);

    public static native long getGlobal();

}
