ThisBuild / organization := "com.github.mikolololoay"
ThisBuild / name := "filmonator-zio"
ThisBuild / scalaVersion := "3.4.2"
ThisBuild / version := "0.1.0-SNAPSHOT"


val zioVersion = "2.1.6"
val sqlliteJdbcVersion = "3.28.0"
val munitVersion = "1.0.0"
val quillZioVersion = "4.8.5"
val kantanCsvVersion = "0.7.0"
val tapirVersion = "1.10.13"

lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % munitVersion % Test,
      "dev.zio" %% "zio" % zioVersion,
      "io.getquill" %% "quill-jdbc-zio" % quillZioVersion,
      "org.xerial" % "sqlite-jdbc" % sqlliteJdbcVersion,
      ("com.nrinaudo" %% "kantan.csv-generic" % kantanCsvVersion).cross(CrossVersion.for3Use2_13),
      "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-zio" % tapirVersion
    )
  )
