package data.analysis

import org.apache.spark.sql.DataFrame

object CSV_import {

  def loadCSV(): DataFrame = {
    val spark = org.apache.spark.sql.SparkSession.builder
      .master("local") //Change it as per your cluster
      .appName("Spark CSV Reader")
      .getOrCreate;

    var df = spark.read
      .format("csv")
      .option("header", "true")
      .load("resources/FakeCsv.csv")

    df
  }
}
