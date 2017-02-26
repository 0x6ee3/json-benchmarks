name := "ColossusBenchmark"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.1"
libraryDependencies += "com.tumblr" %% "colossus" % "0.8.1"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.4.1"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.4.1"

mainClass in (Compile, run) := Some("colossus.examples.main")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
