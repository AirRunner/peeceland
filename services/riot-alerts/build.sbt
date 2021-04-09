name := "riot-alerts"

version := "1.0"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
    "org.apache.kafka" %% "kafka" % "2.7.0",
    "org.scalaj" %% "scalaj-http" % "2.4.2"
)
