package data.analysis

import awscala.File
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}

object LoadToS3 {
  def loadCSV(): Unit = {


    val accessKey = "AKIAWZNQF5NHTMTDNSO7"
    val secretKey = "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"
    val bucketName = "peaceland-reports"

    val credentials = new BasicAWSCredentials(accessKey, secretKey)
    val client: AmazonS3 = AmazonS3ClientBuilder.standard().withRegion("eu-west-2").withCredentials(new AWSStaticCredentialsProvider(credentials)).build()


    client.putObject(bucketName, "file name here", new File("FILE PATH HERE"))


  }
}