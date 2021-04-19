package data.analysis

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Analysis1 {
  def analysis1(df : DataFrame): Unit= {

    //conts eveildoer per day
    val dff= df.as("dff")

    val df1 = dff.withColumn("dateTime", substring(col("dateTime"), 0, 10))


    dff.groupBy("dateTime")
    .agg(count(when(col("peaceScore") < 3 ,1))).show()
  }
}