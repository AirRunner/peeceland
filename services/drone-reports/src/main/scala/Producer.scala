import java.util.Properties
import scala.collection.immutable.Set
import org.apache.kafka.clients.producer._
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write
import java.time.LocalDateTime
import ReportGenerator.Report
import ReportGenerator.Citizen

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
    
    case class Alert (
        dateTime: LocalDateTime = LocalDateTime.now,
        location: String = "0, 0",
        riotCitizens: Set[Citizen] = Set()
    )
    
    def produceReport(report: Report) = {
        implicit val formats = DefaultFormats
        
        val jsonReport = write(report)
        kafkaProduce("drone-reports", "report", jsonReport)
        
        val riotCitizens = report.citizens.filter(cit => cit.peaceScore <= 3)
        
        if (riotCitizens.knownSize > 0) {
            val location = report.latitude + ", " + report.longitude
            val alert = new Alert(report.dateTime, location, riotCitizens)
            
            kafkaProduce("riot-alerts", "alert", write(alert))
        }
    }
}
