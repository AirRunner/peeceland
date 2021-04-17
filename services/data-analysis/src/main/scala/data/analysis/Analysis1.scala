package data.analysis

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Analysis1 {
  def analysis1(df : DataFrame): Unit= {
    df.groupBy("Date").agg(count(when(col("Peace_Score") < 3 ,1))).show()
  }

}
