package data.analysis

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Analysis2 {
  def analysis2(df : DataFrame): Unit= {

    //conts eveildoer per day
    val dff= df.as("dff")

    val df1 = dff.withColumn("dateTime", substring(col("dateTime"), 0, 10)).
      withColumn("watcherId",col("watcherId").cast("int"))


    df1.groupBy("watcherId")
      .agg(count(when(col("peaceScore") <= 3 ,1))).sort("watcherId").show()
  }
}