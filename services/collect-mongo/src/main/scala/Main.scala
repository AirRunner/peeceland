import com.mongodb.spark._
import org.apache.spark.sql.SparkSession

object Main {
    def main(args: Array[String]): Unit = {
        val mongoConnectionURL = "mongodb+srv://peaceland:9CmNkjVzR1e1xR0v@devcluster.4jen4.mongodb.net/?retryWrites=true&w=majority"
        val spark = SparkSession.builder()
            .master("local")
            .appName("MongoSpark")
            .config("spark.mongodb.input.uri", mongoConnectionURL)
            .config("spark.mongodb.output.uri", mongoConnectionURL)
            .config("spark.mongodb.input.database", "peaceland")
            .config("spark.mongodb.input.collection", "drone-reports")
            .getOrCreate()
        
        val mongoRdd = MongoSpark.load(spark.sparkContext)
        mongoRdd.toDF.write.format("csv").save("peeceland/services/data-storage/resources/resources.csv")
    }
}
