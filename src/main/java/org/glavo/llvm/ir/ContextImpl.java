package org.glavo.llvm.ir;

import org.glavo.llvm.JNIUtils;

public final class ContextImpl {
    static {
        JNIUtils.load();
    }

    public static native long newInstance();

    public static native void delete(long handle);

    public static native long getGlobal();

}
