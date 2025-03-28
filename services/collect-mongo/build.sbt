name := "data-storage"

version := "1.0"

scalaVersion := "2.12.13"

val sparkVersion = "3.0.1"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "org.mongodb.spark" %% "mongo-spark-connector" % sparkVersion
)
