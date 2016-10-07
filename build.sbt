name := "todo-backend-akka"

packageArchetype.java_application

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked","-deprecation", "-feature")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core" % "2.4.11",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.11",
  "com.typesafe.akka" %% "akka-http-testkit" % "2.4.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

Revolver.settings
