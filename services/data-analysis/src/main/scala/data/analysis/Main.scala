package data.analysis

import org.apache.log4j.BasicConfigurator

object Main {
  def main(args: Array[String]): Unit = {
    BasicConfigurator.configure()

    val df = CsvFromS3.loadCSV()

    df.printSchema()
    Analysis1.analysis1(df)
    Analysis3.WhereAreNotpeacufulPeople(df)
  }
}
