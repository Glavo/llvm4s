package org.glavo.llvm.ir;

import org.glavo.llvm.Handle;
import org.glavo.llvm.NativeImpl;
import org.glavo.llvm.Unsigned;

public final class IntegerTypeImpl extends NativeImpl {
    public static native @Handle(IntegerType.class) long get(@Handle(Context.class) long context, @Unsigned int numBits);

    public static native @Unsigned int getBitWidth(@Handle(IntegerType.class) long handle);

    public static native @Unsigned long getBitMask(@Handle(IntegerType.class) long handle);

    public static native @Unsigned long getSignBit(@Handle(IntegerType.class) long handle);

    public static native boolean isPowerOf2ByteWidth(@Handle(IntegerType.class) long handle);

}
