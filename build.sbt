inThisBuild(Seq(
  scalaVersion := "2.12.4",
  crossScalaVersions := Seq("2.12.4", "2.11.11"),
  target in javah := file("target") / "jni-include"
))


lazy val all = (project in file("."))
  .settings(
    name := "llvm4s",
    version := "0.1.0",
    crossScalaVersions := Seq("2.12.4", "2.11.11"),
  ).dependsOn(core, `linux-x86`, `linux-x86_64`, `macosx-x86_64`, `windows-x86`, `windows-x86_64`)

lazy val core = (project in file("core"))
  .settings(
    name := "llvm4s-core",
    version := "0.1.0",
    target in javah := file("target") / "jni-include"
  )

lazy val `linux-x86` = (project in file("linux-x86"))
  .settings(
    name := "llvm4s-linux-x86",
    version := "5.0.1"
  )

lazy val `linux-x86_64` = (project in file("linux-x86_64"))
  .settings(
    name := "llvm4s-linux-x86_64",
    version := "5.0.1"
  )

lazy val `macosx-x86_64` = (project in file("macosx-x86_64"))
  .settings(
    name := "llvm4s-macosx-x86_64",
    version := "5.0.1"
  )

lazy val `windows-x86` = (project in file("windows-x86"))
  .settings(
    name := "llvm4s-windows-x86",
    version := "5.0.1"
  )


lazy val `windows-x86_64` = (project in file("windows-x86_64"))
  .settings(
    name := "llvm4s-windows-x86_64",
    version := "5.0.1"
  )
