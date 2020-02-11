
val phantomVersion              = "2.13.5"

lazy val `libs` =
  Seq(
    "com.outworkers"             %% "phantom-dsl"                  % phantomVersion,
    "org.apache.kafka"           % "kafka-clients"                 % "0.10.1.0",
    "com.typesafe.scala-logging" %% "scala-logging"                % "3.9.0",
    "com.typesafe.akka"          %% "akka-http"                    % "10.1.11",
    "com.typesafe.akka"          %% "akka-stream"                  % "2.5.26",
    "com.typesafe.akka"          %% "akka-http-spray-json"         % "10.1.11",
    "org.slf4j"                  % "slf4j-simple"                  % "1.6.2",
    "org.typelevel"              %% "cats-core"                    % "0.9.0",
    "com.softwaremill.quicklens" %% "quicklens"                    % "1.4.11",
    "com.thesamet.scalapb"       %% "scalapb-runtime"              % scalapb.compiler.Version.scalapbVersion % "protobuf"

  )

lazy val commonSettings = Seq(
    version := "0.1",
    scalaVersion := "2.12.2",
    target := { baseDirectory.value / "target" },
    PB.targets in Compile := Seq(
      scalapb.gen() -> (sourceManaged in Compile).value
    )
)

lazy val `core` = (project in file("books/core")).settings(commonSettings, libraryDependencies ++= `libs` )

lazy val `kafka` = (project in file("books/kafka")).settings(commonSettings, libraryDependencies ++= `libs` ).dependsOn(core)

lazy val `http` = (project in file("books/http")).settings(commonSettings, libraryDependencies ++= `libs` ).dependsOn(core)

lazy val `root` = (project in file("."))
  .aggregate(kafka, http, core)
