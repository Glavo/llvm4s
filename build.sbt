val llvm4sVersion = "0.1.0"

inThisBuild(Seq(
  scalaVersion := "2.12.4",
  crossScalaVersions := Seq("2.12.4", "2.11.11"),
  target in javah := file("target") / "jni-include"
))


lazy val all = (project in file("."))
  .settings(
    name := "llvm4s",
    version := llvm4sVersion,
    crossScalaVersions := Seq("2.12.4", "2.11.11"),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  ).dependsOn(core, `linux-x86`, `linux-x86_64`, `macosx-x86_64`, `windows-x86`, `windows-x86_64`)

lazy val internal = (project in file("internal"))
  .settings(
    name := "llvm4s-internal",
    version := llvm4sVersion,
    autoScalaLibrary := false,
    target in javah := file("target") / "jni-include"
  )

lazy val core = (project in file("core"))
  .settings(
    name := "llvm4s-core",
    version := llvm4sVersion
  ).dependsOn(internal)

lazy val `linux-x86` = (project in file("linux-x86"))
  .settings(
    name := "llvm4s-linux-x86",
    version := llvm4sVersion,
    autoScalaLibrary := false
  )

lazy val `linux-x86_64` = (project in file("linux-x86_64"))
  .settings(
    name := "llvm4s-linux-x86_64",
    version := llvm4sVersion,
    autoScalaLibrary := false
  )

lazy val `macosx-x86_64` = (project in file("macosx-x86_64"))
  .settings(
    name := "llvm4s-macosx-x86_64",
    version := llvm4sVersion,
    autoScalaLibrary := false
  )

lazy val `windows-x86` = (project in file("windows-x86"))
  .settings(
    name := "llvm4s-windows-x86",
    version := llvm4sVersion,
    autoScalaLibrary := false
  )


lazy val `windows-x86_64` = (project in file("windows-x86_64"))
  .settings(
    name := "llvm4s-windows-x86_64",
    version := llvm4sVersion,
    autoScalaLibrary := false
  )
