package org.glavo.llvm.internal;

public abstract class NativeImpl {
    static {
        JNIUtils.load();
    }
}
