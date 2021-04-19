import com.mongodb.spark._

object MongoSpark {


  def main(args: Array[String]): Unit = {
    //Start the Spark context

    val mongoConnectionURI = "mongodb+srv://peaceland:9CmNkjVzR1e1xR0v@devcluster.4jen4.mongodb.net/?retryWrites=true&w=majority"

    val spark = SparkSession
      .builder()
      .master("local")
      .appName("MongoSpark")
      .config("spark.mongodb.input.uri", mongoConnectionURI)
      .config("spark.mongodb.output.uri", mongoConnectionURI)
      .getOrCreate()

    //Read data from MongoDB
    val df = MongoSpark.load(spark)
    df.write.format("csv").save("peeceland/services/data-storage/resources")

  }
}