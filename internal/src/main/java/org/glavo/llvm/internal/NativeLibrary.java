package org.glavo.llvm.internal;

import java.util.HashMap;
import java.util.Map;

public class NativeLibrary {
    public static final Map<String, String[]> libNames = new HashMap<>();

    static {
        libNames.put("linux-x86", new String[]{"LLVM-5.0", "LTO", "llvm4s"});
        libNames.put("linux-x86_64", new String[]{"LLVM-5.0", "LTO", "llvm4s"});
        libNames.put("macosx-x86_64", new String[]{"LLVM-5.0", "LTO", "llvm4s"});
    }
}
