name := "peeceland"

version := "0.1"

scalaVersion := "2.12.13"

//val sparkVersion = "3.0.1"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.1.4",
    "org.scalacheck" %% "scalacheck" % "1.14.1",
    "net.liftweb" %% "lift-json" % "3.4.3",
    "org.apache.kafka" %% "kafka" % "2.3.1",
    "org.mongodb.scala" %% "mongo-scala-driver" % "4.2.2",
    //"com.google.code.gson" % "gson" % "2.8.6",
    //"org.apache.spark" %% "spark-core" % sparkVersion,
    //"org.apache.spark" %% "spark-sql" % sparkVersion
)
