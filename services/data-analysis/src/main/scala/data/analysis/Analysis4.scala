package data.analysis

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._


object Analysis4 {
  def analysis4(df : DataFrame): Unit= {

    //conts eveildoer per day
    val dff= df.as("dff")

    val df1 = dff.withColumn("dateTime", substring(col("dateTime"), 0, 10)).
      withColumn("watcherId",col("watcherId").cast("int"))

    df1.select(
      col("dateTime"),
      to_date(col("dateTime"),"yyyy-MM-dd").as("to_date")
    )

    df1.withColumn("dateTime_Ts",
      to_timestamp(col("input_timestamp")))
      .withColumn("week_day_number", date_format(col("dateTime_Ts"), "u"))
      .withColumn("week_day_abb", date_format(col("dateTime_Ts"), "E"))
      .show(false)

    df1.groupBy("week_day_number")
      .agg(mean(when(col("peaceScore") <= 3 ,1))).sort("week_day_number").show()

  }
}