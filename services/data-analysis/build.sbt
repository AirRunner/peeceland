name := "data-analysis"

version := "1.0"

scalaVersion := "2.12.13"

val sparkVersion = "3.0.1"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "jp.co.bizreach" %% "aws-s3-scala" % "0.0.15",
    "org.apache.hadoop"%"hadoop-common" % "3.3.0",
    "org.apache.hadoop"%"hadoop-client"%"3.3.0",
    "org.apache.hadoop"%"hadoop-aws"%"3.3.0"

)

