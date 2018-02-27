package org.glavo.llvm.ir;

import org.glavo.llvm.*;
import org.glavo.llvm.Enum;

public final class TypeImpl extends NativeImpl {
    public static native @Handle(Type.class) long getVoidTy(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getLabel(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getHalfTy(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getFloatTy(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getDoubleTy(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getMetadataTy(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getX86_FP80Ty(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getFP128Ty(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getPPC_FP128Ty(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getX86_MMXTy(@Handle(Context.class) long context);

    public static native @Handle(Type.class) long getTokenTy(@Handle(Context.class) long context);


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
