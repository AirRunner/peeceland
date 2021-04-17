package data.analysis

object Main {
  def main(args: Array[String]): Unit = {

    var df=CSV_import.loadCSV()
    df.printSchema()

  }
}
