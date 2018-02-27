package org.glavo.llvm.ir;

import org.glavo.llvm.Handle;
import org.glavo.llvm.NativeImpl;

public final class FunctionTypeImpl extends NativeImpl {
    public static native @Handle(FunctionType.class) long get(@Handle(Type.class) long result, @Handle(Type.class) long[] params, boolean isVarArg);

    public static native boolean isValidReturnType(@Handle(Type.class) long retType);

    public static native boolean isValidArgumentType(@Handle(Type.class) long argType);

    public static native boolean isVarArg(@Handle(FunctionType.class) long handle);

    public static native @Handle(Type.class) long getRetunrnType(@Handle(FunctionType.class) long handle);

    public static native @Handle(Type.class) long[] getParamTypes(@Handle(FunctionType.class) long handle);
}
