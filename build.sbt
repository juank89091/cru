name := "cru"

version := "0.1"

scalaVersion := "2.12.2"

val phantomVersion              = "2.13.5"


libraryDependencies ++=
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
//    "io.grpc"                    % "grpc-netty"                    % scalapb.compiler.Version.grpcJavaVersion,
//    "com.thesamet.scalapb"       %% "scalapb-runtime-grpc"         % scalapb.compiler.Version.scalapbVersion

  )


