package data.analysis

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Analysis3 {
  def WhereAreNotpeacufulPeople(df : DataFrame): Unit= {
    val dff = df.withColumn("dateTime", substring(col("dateTime"), 0, 10))
    dff.groupBy("dateTime","Lat-Long").count().show()

  }
}
