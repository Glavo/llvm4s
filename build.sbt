name := "llvm4s"

version := "0.1.0"

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.12.4", "2.11.11")

libraryDependencies += "org.bytedeco.javacpp-presets" % "llvm-platform" % "5.0.1-1.4"

fork := true