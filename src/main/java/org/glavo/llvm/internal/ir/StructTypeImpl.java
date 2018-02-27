package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.ByteString;
import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;
import org.glavo.llvm.ir.Context;
import org.glavo.llvm.ir.StructType;
import org.glavo.llvm.ir.Type;

public class StructTypeImpl extends NativeImpl {
    public static native @Handle(StructType.class) long create(
            @Handle(Context.class) long contextHandle,
            @Handle(Type.class) long[] typeHandles,
            @ByteString byte[] name,
            boolean isPacked
    );

    public static native @Handle(StructType.class) long get(
            @Handle(Context.class) long contextHandle,
            @Handle(Type.class) long[] elemTypeHandles,
            boolean isPacked
    );

    public static native boolean isValidElementType(@Handle(StructType.class) long handle);


    public static native boolean isPacked(@Handle(StructType.class) long handle);

    public static native boolean isLiteral(@Handle(StructType.class) long handle);

    public static native boolean isQpaque(@Handle(StructType.class) long handle);

    public static native boolean hasName(@Handle(StructType.class) long handle);

    public static native @ByteString byte[] getName(@Handle(StructType.class) long handle);

    public static native void setName(@Handle(StructType.class) long handle, @ByteString byte[] name);

    public static native @Handle(Type.class) long[] getElements(@Handle(StructType.class) long handle);

    public static native @Unsigned int getNumElements(@Handle(StructType.class) long handle);

    public static native @Handle(Type.class) long getElementType(@Handle(StructType.class) long handle, @Unsigned int n);

    public static native void setBody(@Handle(StructType.class) long handle, @Handle(Type.class) long[] elementHandles, boolean isPacked);
}
