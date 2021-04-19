import com.mongodb.spark._

object MongoSpark {

  case class students_cc(id: Int,
                         year_graduated: String,
                         courses_registered: List[cid_sem],
                         name: String)
  case class cid_sem(cid: String, sem: String)

  def main(args: Array[String]): Unit = {
    //Start the Spark context

    val mongoConnectionURL = "mongodb+srv://peaceland:9CmNkjVzR1e1xR0v@devcluster.4jen4.mongodb.net/?retryWrites=true&w=majority"

    val spark = SparkSession
      .builder()
      .master("local")
      .appName("MongoSpark")
      .config("spark.mongodb.input.uri", mongoConnectionURL)
      .config("spark.mongodb.output.uri", mongoConnectionURL)
      .getOrCreate()

    //Read data from MongoDB
    val df = MongoSpark.load(spark)
    df.write.format("csv").save("peeceland/services/data-storage/resources")

  }
}