package data.analysis

import org.apache.log4j.BasicConfigurator

object Main {
  def main(args: Array[String]): Unit = {
    BasicConfigurator.configure()

    val df = CsvFromS3.loadCSV()


    Analysis1.analysis1(df)
    Analysis2.analysis2(df)
    Analysis3.WhereAreNotpeacufulPeople(df)
    df.printSchema()
  }
}
