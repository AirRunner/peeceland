//import ReportGenerator.jsonReport
import Kafka.kafkaConsume

object Main {
    def main(args: Array[String]): Unit = {
        //val droneReport = jsonReport()
        kafkaConsume("drone-reports")
    }
}
