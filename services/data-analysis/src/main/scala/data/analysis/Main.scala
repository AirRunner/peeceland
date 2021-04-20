package data.analysis

import org.apache.log4j.{BasicConfigurator, Level, Logger}
import org.slf4j.LoggerFactory

object Main {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    Logger.getLogger("io").setLevel(Level.OFF)
    Logger.getLogger("com").setLevel(Level.OFF)



    BasicConfigurator.configure()



    val df = CsvFromS3.loadCSV()


    Analysis1.analysis1(df)
    Analysis2.analysis2(df)
    Analysis3.WhereAreEvilDoers(df)
    df.printSchema()
  }
}
