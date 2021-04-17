import org.apache.spark.sql.SparkSession

object SparkWordCount extends App {

    val spark = SparkSession.builder
      .master("local[*]")
      .appName("Spark Load Csv")
      .getOrCreate()


    val df = spark.read.option("header",true).csv("resources/FakeCsv.csv")
    df.printSchema()
}