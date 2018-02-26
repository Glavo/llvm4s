name := "llvm4s"

version := "0.1.0"

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.12.4", "2.11.11")

target in javah := file("target") / "jni-include"