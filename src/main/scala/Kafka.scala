import java.util
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.producer._
import org.apache.kafka.clients.consumer.KafkaConsumer

object Kafka {
    
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
    
    def handleMessages(cons: KafkaConsumer[String, String]): Unit = {
        val record = cons.poll(1000).asScala
        record.iterator.foreach(msg => {
            println(msg.value())
            
            // produce riot alerts
            //kafkaProduce("riot-alerts", dateTime, citizenName + score)
            kafkaProduce("riot-alerts", "riot-alerts", msg.value()) //testing
        })
        handleMessages(cons)
    }
    
    def kafkaConsume(topic: String) = {
        val props = new Properties()
        props.put("bootstrap.servers", "localhost:9092")
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        props.put("auto.offset.reset", "latest")
        props.put("group.id", "consumer-group")
        
        val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
        consumer.subscribe(util.Arrays.asList(topic))
        
        handleMessages(consumer)
    }
}
                