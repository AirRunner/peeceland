package data.analysis

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.IntegerType

object Analysis3 {
  def WhereAreEvilDoers(df : DataFrame): Unit= {
    val dff = df.withColumn("dateTime", substring(col("dateTime"), 0, 10))

    dff.groupBy("dateTime","latitude", "longitude").agg(count(when(col("peaceScore") <= 3 ,1))).sort("dateTime")show()

  }
}