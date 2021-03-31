import java.util
import java.util.Properties
import java.io.Serializable
import scala.collection.JavaConverters._
import org.apache.kafka.clients.producer._
import org.apache.kafka.clients.consumer.KafkaConsumer
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import ReportGenerator.Report

object Producer {
    def kafkaProduce(topic: String, key: String, value: String): Unit = {
        val props = new Properties()
        props.put("bootstrap.servers", "localhost:9092")
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        
        val producer = new KafkaProducer[String, String](props)
        val record = new ProducerRecord[String, String](topic, key, value)
        
        producer.send(record)
        producer.close()
    }
    
    def produceReport(report: Report) = {
        implicit val formats = DefaultFormats
        
        val jsonReport = write(report)
        kafkaProduce("drone-reports", "report", jsonReport)
        
        val riotCitizens = report.citizens.filter(cit => cit.peaceScore <= 3)
        
        if (riotCitizens.length > 0) {
            val jsonRiots = write(riotCitizens)
            kafkaProduce("riot-alerts", "alert", jsonRiots)
        }
    }
}
