package org.glavo.llvm.internal.ir;

import org.glavo.llvm.internal.Handle;
import org.glavo.llvm.internal.NativeImpl;

public final class FunctionTypeImpl extends NativeImpl {
    public static native @Handle("FunctionType") long get(@Handle("Type") long result, @Handle("Type") long[] params, boolean isVarArg);

    public static native boolean isValidReturnType(@Handle("Type") long retType);

    public static native boolean isValidArgumentType(@Handle("Type") long argType);

    public static native boolean isVarArg(@Handle("FunctionType") long handle);

    public static native @Handle("Type") long getReturnType(@Handle("FunctionType") long handle);

    public static native @Handle("Type") long[] getParamTypes(@Handle("FunctionType") long handle);
}
