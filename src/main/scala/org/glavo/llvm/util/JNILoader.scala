package org.glavo.llvm.util

object JNILoader {
  val jvmName: String = System.getProperty("java.vm.name", "").toLowerCase

  val osName: String = System.getProperty("os.name", "").toLowerCase match {
    case s if jvmName.startsWith("dalvik") && s.startsWith("linux") =>
      "android"
    case s if s.startsWith("robovm") && s.startsWith("darwin") =>
      "ios"
    case s if s.startsWith("mac os x") || s.startsWith("darwin") =>
      "macosx"
    case s if s.contains("windows") =>
      "windows"
    case s =>
      val i = osName.indexOf(' ')
      if (i > 0)
        s.substring(0, i)
      else
        s
  }

  val osArch: String = System.getProperty("os.arch", "").toLowerCase match {
    case "i386" | "i486" | "i586" | "i686" =>
      "x86"
    case "amd64" | "x86-64" | "x64" =>
      "x86_64"
    case s if s.startsWith("aarch64") || s.startsWith("armv8") || s.startsWith("arm64") =>
      "arm64"
    case s if s.startsWith("arm") ||
      System.getProperty("sun.arch.abi", "").toLowerCase == "gnueabihf"
      || System.getProperty("sun.boot.library.path", "").toLowerCase.contains("openjdk-armhf") =>
      "armhf"
    case s if s.startsWith("arm") || osName == "ios" =>
      "arm"
    case s => s
  }

  val platform: String = s"$osName-$osArch"

  val suffix: String =osName match {
    case "windows" => ".dll"
    case "macosx" => ".dylib"
    case _ => ".so"
  }

  def load(c: Class[_], libName: String): Unit = {

  }
}
