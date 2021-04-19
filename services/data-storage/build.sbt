name := "data-storage"

version := "1.0"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
    "org.apache.kafka" %% "kafka" % "2.7.0",
    "org.mongodb.scala" %% "mongo-scala-driver" % "4.2.3"
)
