name := "drone-reports"

version := "1.0"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
    "org.scalacheck" %% "scalacheck" % "1.15.3",
    "joda-time" % "joda-time" % "2.10.10",
    "net.liftweb" %% "lift-json" % "3.4.3",
    "org.apache.kafka" %% "kafka" % "2.7.0"
)
