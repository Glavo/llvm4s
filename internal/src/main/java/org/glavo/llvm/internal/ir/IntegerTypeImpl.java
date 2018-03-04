package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;

public final class IntegerTypeImpl extends NativeImpl {
    public static native @Handle("IntegerType") long get(@Handle("Context") long context, @Unsigned int numBits);

    public static native @Unsigned int getBitWidth(@Handle("IntegerType") long handle);

    public static native @Unsigned long getBitMask(@Handle("IntegerType") long handle);

    public static native @Unsigned long getSignBit(@Handle("IntegerType") long handle);

    public static native boolean isPowerOf2ByteWidth(@Handle("IntegerType") long handle);

}
