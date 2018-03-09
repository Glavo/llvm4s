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
  ).dependsOn(core, internal)

lazy val core = (project in file("core"))
  .settings(
    name := "llvm4s-core",
    version := llvm4sVersion
  ).dependsOn(internal)

lazy val internal = (project in file("internal"))
  .settings(
    name := "llvm4s-internal",
    version := llvm4sVersion,
    autoScalaLibrary := false,
    target in javah := file("internal") / "src" / "main" / "jni-include"
  )