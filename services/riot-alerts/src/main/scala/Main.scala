import Consumer.kafkaConsume

object Main {
    def main(args: Array[String]): Unit = {
        kafkaConsume("riot-alerts")
    }
}
