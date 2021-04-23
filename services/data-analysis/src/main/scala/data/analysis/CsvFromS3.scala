package data.analysis


import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.services.s3.model.ListObjectsRequest
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}

import concurrent.ExecutionContext.Implicits._
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.log4j.Logger
import org.apache.log4j.Level
import sun.awt.SunHints.Key


object CsvFromS3 {
  def loadCSV(): DataFrame= {
    val accessKey = "AKIAWZNQF5NHTMTDNSO7"
    val secretKey = "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"
    val bucketName = "peaceland-reports"

    val credentials = new BasicAWSCredentials(accessKey, secretKey)
    val client: AmazonS3 = AmazonS3ClientBuilder.standard().withRegion("eu-west-2").withCredentials(new AWSStaticCredentialsProvider(credentials)).build()




    val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("loadCsvFromS3")
      .getOrCreate()
    // Replace Key with your AWS account key (You can find this on IAM
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.access.key", "AKIAWZNQF5NHTMTDNSO7")
    // Replace Key with your AWS secret key (You can find this on IAM
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.secret.key", "R+2uyxIEVs2aMwZuRVs9mwrN4N7ccq8X3Yz2pnKQ")
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.endpoint", "s3.amazonaws.com")
    spark.sparkContext.setLogLevel("ERROR")
    
    // Legacy datetime parser
    spark.conf.set("spark.sql.legacy.timeParserPolicy","LEGACY")



    val df = spark.read
      .format("csv")
      .option("header", "true")
      .load("s3a://peaceland-reports/data/reports.csv")
    //df.show(false)
    //df.printSchema()

    //val stock = client.listObjects(bucketName,"data/").getObjectSummaries()



    //println(stock)

    df
  }


}

