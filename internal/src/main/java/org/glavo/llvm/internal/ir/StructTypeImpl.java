package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.ByteString;
import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;
import org.glavo.llvm.internal.Unsigned;

public class StructTypeImpl extends NativeImpl {
    public static native @Handle("StructType") long create(
            @Handle("Context") long contextHandle,
            @Handle("Type") long[] typeHandles,
            @ByteString byte[] name,
            boolean isPacked
    );

    public static native @Handle("StructType") long get(
            @Handle("Context") long contextHandle,
            @Handle("Type") long[] elemTypeHandles,
            boolean isPacked
    );

    public static native boolean isValidElementType(@Handle("StructType") long handle);


    public static native boolean isPacked(@Handle("StructType") long handle);

    public static native boolean isLiteral(@Handle("StructType") long handle);

    public static native boolean isQpaque(@Handle("StructType") long handle);

    public static native boolean hasName(@Handle("StructType") long handle);

    public static native @ByteString byte[] getName(@Handle("StructType") long handle);

    public static native void setName(@Handle("StructType") long handle, @ByteString byte[] name);

    public static native @Handle("Type") long[] getElements(@Handle("StructType") long handle);

    public static native @Unsigned int getNumElements(@Handle("StructType") long handle);

    public static native @Handle("Type") long getElementType(@Handle("StructType") long handle, @Unsigned int n);

    public static native void setBody(@Handle("StructType") long handle, @Handle("Type") long[] elementHandles, boolean isPacked);
}
