package org.glavo.llvm.ir;

import org.glavo.llvm.*;
import org.glavo.llvm.Enum;

public final class TypeImpl extends NativeImpl {
    public static native @ByteString byte[] toString(@Handle(Type.class) long handle);

    public static native @Handle(Context.class) long getContext(@Handle(Type.class) long handle);

    public static native @Enum int getTypeId(@Handle(Type.class) long handle);

    public static native boolean isFPOrFPVectorTy(@Handle(Type.class) long handle);

    public static native boolean isIntegerTy(@Handle(Type.class) long handle, @Unsigned int bitWidth);

    public static native boolean isIntOrIntVectorTy(@Handle(Type.class) long handle);

    public static native boolean isIntOrIntVectorTy(@Handle(Type.class) long handle, @Unsigned int bitWidth);

    public static native boolean isPtrOrPtrVectorTy(@Handle(Type.class) long handle);

    public static native boolean canLosslesslyBitCastTo(@Handle(Type.class) long thisHandle, @Handle(Type.class) long otherHandle);

    public static native boolean isEmptyTy(@Handle(Type.class) long handle);

    public static native @Unsigned int getPrimitiveSizeInBits(@Handle(Type.class) long handle);

    public static native @Unsigned int getScalarSizeInBits(@Handle(Type.class) long handle);

    public static native int getFPMantissaWidth(@Handle(Type.class) long handle);
}
