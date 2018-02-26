package org.glavo.llvm;

public abstract class NativeImpl {
    static {
        JNIUtils.load();
    }
}
