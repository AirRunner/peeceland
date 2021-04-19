package data.analysis

import org.apache.log4j.BasicConfigurator

object Main {
  def main(args: Array[String]): Unit = {
    BasicConfigurator.configure()
    //var df=CSV_import.loadCSV()
    //df.printSchema()

    val df = CsvFromS3.loadCSV()

    Analysis1.analysis1(df)
  }
}
