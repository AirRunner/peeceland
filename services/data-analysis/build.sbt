name := "data-analysis"

version := "1.0"

scalaVersion := "2.12.13"

val sparkVersion = "3.0.1"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-sql" % sparkVersion,
    "jp.co.bizreach" %% "aws-s3-scala" % "0.0.15",
    "com.amazonaws" % "aws-java-sdk" % "1.0.002",
    "org.apache.hadoop"%"hadoop-common" % "3.3.0",
    "org.apache.hadoop"%"hadoop-client"%"3.3.0",
    "org.apache.hadoop"%"hadoop-aws"%"3.3.0",
    "com.amazonaws" % "aws-java-sdk-bom" % "1.11.391",
    "com.amazonaws" % "aws-java-sdk-s3"  % "1.11.391",
    "net.kaliber" %% "play-s3" % "9.0.0"

)

