import jp.co.bizreach.kinesis._
import com.amazonaws.regions.Regions

object Main {
    def main(args: Array[String]): Unit = {
        implicit val region = Regions.EU_WEST_2
        
        // Credentials ?
        val client = AmazonKinesis()
        
        val request = PutRecordRequest(
            streamName   = "drone reports",
            partitionKey = "drone-reports",
            data         = "drone report".getBytes("UTF-8")
        )
        
        client.putRecordWithRetry(request)
    }
}
