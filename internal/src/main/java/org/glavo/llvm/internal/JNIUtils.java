package org.glavo.llvm.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public final class JNIUtils {
    public static final String encoding = "UTF-8";

    private static final String PLATFORM;

    public static final String PREFIX;
    public static final String SUFFIX;


    static {
        String jvmName = System.getProperty("java.vm.name", "").toLowerCase();
        String osName = System.getProperty("os.name", "").toLowerCase();
        String osArch = System.getProperty("os.arch", "").toLowerCase();
        String abiType = System.getProperty("sun.arch.abi", "").toLowerCase();
        String libPath = System.getProperty("sun.boot.library.path", "").toLowerCase();
        if (jvmName.startsWith("dalvik") && osName.startsWith("linux")) {
            osName = "android";
        } else if (jvmName.startsWith("robovm") && osName.startsWith("darwin")) {
            osName = "ios";
            osArch = "arm";
        } else if (osName.startsWith("mac os x") || osName.startsWith("darwin")) {
            osName = "macosx";
        } else if (osName.contains("windows")) {
            osName = "windows";
        } else {
            int spaceIndex = osName.indexOf(' ');
            if (spaceIndex > 0) {
                osName = osName.substring(0, spaceIndex);
            }
        }
        if (osArch.equals("i386") || osArch.equals("i486") || osArch.equals("i586") || osArch.equals("i686")) {
            osArch = "x86";
        } else if (osArch.equals("amd64") || osArch.equals("x86-64") || osArch.equals("x64")) {
            osArch = "x86_64";
        } else if (osArch.startsWith("aarch64") || osArch.startsWith("armv8") || osArch.startsWith("arm64")) {
            osArch = "arm64";
        } else if ((osArch.startsWith("arm")) && ((abiType.equals("gnueabihf")) || (libPath.contains("openjdk-armhf")))) {
            osArch = "armhf";
        } else if (osArch.startsWith("arm")) {
            osArch = "arm";
        }

        PLATFORM = osName + "-" + osArch;

        switch (osName) {
            case "windows":
                PREFIX = "";
                SUFFIX = ".dll";
                break;
            case "macosx":
            case "ios":
                PREFIX = "lib";
                SUFFIX = ".dylib";
                break;
            default:
                PREFIX = "lib";
                SUFFIX = ".so";
        }
    }

    public static void load(URL url) {
        try (InputStream stream = url.openStream()) {
            Path temp = Files.createTempFile(PREFIX, SUFFIX);
            Files.deleteIfExists(temp);
            Files.copy(stream, temp);
            System.load(temp.toAbsolutePath().toFile().toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static boolean isLoaded = false;

    public static void load() {
        if (!isLoaded) {
            isLoaded = true;
            try {
                Class<?> cls = Class.forName(
                        String.format(
                                "%s.%s_%s",
                                NativeLibrary.class.getPackage().getName(),
                                NativeLibrary.class.getSimpleName(),
                                PLATFORM.replace('-', '_')
                        )
                );
                String[] libNames = (String[]) cls.getField("libNames").get(null);

                for (String libName : libNames) {
                    load(Objects.requireNonNull(cls.getResource(String.format("%s/%s%s%s", PLATFORM, PREFIX, libName, SUFFIX))));
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public static String bytes2str(byte[] bytes) throws UnsupportedEncodingException {
        Objects.requireNonNull(bytes);

        return new String(bytes, encoding);
    }

    public static byte[] str2bytes(String str) throws UnsupportedEncodingException {
        Objects.requireNonNull(str);
        return str.getBytes(encoding);
    }
}
