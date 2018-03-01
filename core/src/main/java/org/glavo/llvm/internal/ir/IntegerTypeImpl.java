package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;
import org.glavo.llvm.ir.Context;
import org.glavo.llvm.ir.IntegerType;

public final class IntegerTypeImpl extends NativeImpl {
    public static native @Handle(IntegerType.class) long get(@Handle(Context.class) long context, @Unsigned int numBits);

    public static native @Unsigned int getBitWidth(@Handle(IntegerType.class) long handle);

    public static native @Unsigned long getBitMask(@Handle(IntegerType.class) long handle);

    public static native @Unsigned long getSignBit(@Handle(IntegerType.class) long handle);

    public static native boolean isPowerOf2ByteWidth(@Handle(IntegerType.class) long handle);

}
