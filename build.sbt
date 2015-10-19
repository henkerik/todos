name := "todo-backend-akka"

packageArchetype.java_application

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-http-testkit-experimental_2.11" % "1.0" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)
