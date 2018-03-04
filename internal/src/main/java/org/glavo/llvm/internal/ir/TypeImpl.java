package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.*;
import org.glavo.llvm.internal.Enum;

public final class TypeImpl extends NativeImpl {
    public static native @Handle("Type") long getVoidTy(@Handle("Context") long context);

    public static native @Handle("Type") long getLabel(@Handle("Context") long context);

    public static native @Handle("Type") long getHalfTy(@Handle("Context") long context);

    public static native @Handle("Type") long getFloatTy(@Handle("Context") long context);

    public static native @Handle("Type") long getDoubleTy(@Handle("Context") long context);

    public static native @Handle("Type") long getMetadataTy(@Handle("Context") long context);

    public static native @Handle("Type") long getX86_FP80Ty(@Handle("Context") long context);

    public static native @Handle("Type") long getFP128Ty(@Handle("Context") long context);

    public static native @Handle("Type") long getPPC_FP128Ty(@Handle("Context") long context);

    public static native @Handle("Type") long getX86_MMXTy(@Handle("Context") long context);

    public static native @Handle("Type") long getTokenTy(@Handle("Context") long context);


    public static native @ByteString byte[] toString(@Handle("Type") long handle);

    public static native @Handle("Context") long getContext(@Handle("Type") long handle);

    public static native @Enum int getTypeId(@Handle("Type") long handle);

    public static native boolean isFPOrFPVectorTy(@Handle("Type") long handle);

    public static native boolean isIntegerTy(@Handle("Type") long handle, @Unsigned int bitWidth);

    public static native boolean isIntOrIntVectorTy(@Handle("Type") long handle);

    public static native boolean isIntOrIntVectorTy(@Handle("Type") long handle, @Unsigned int bitWidth);

    public static native boolean isPtrOrPtrVectorTy(@Handle("Type") long handle);

    public static native boolean canLosslesslyBitCastTo(@Handle("Type") long thisHandle, @Handle("Type") long otherHandle);

    public static native boolean isEmptyTy(@Handle("Type") long handle);

    public static native @Unsigned int getPrimitiveSizeInBits(@Handle("Type") long handle);

    public static native @Unsigned int getScalarSizeInBits(@Handle("Type") long handle);

    public static native int getFPMantissaWidth(@Handle("Type") long handle);
}
