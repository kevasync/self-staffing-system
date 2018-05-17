name := "http4s-service"

version := "0.1"

scalaVersion := "2.12.6"

val http4sVersion = "0.19.0-SNAPSHOT"
val circeVersion = "0.10.0-M1"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "org.squeryl" %% "squeryl" % "0.9.5-7",
  "net.sourceforge.jtds" % "jtds" % "1.3.1",

  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-literal" % circeVersion
)

scalacOptions ++= Seq("-Ypartial-unification")