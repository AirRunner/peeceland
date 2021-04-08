import java.util
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer

object Consumer {
    def handleMessages(cons: KafkaConsumer[String, String]): Unit = {
        val record = cons.poll(500).asScala
        record.iterator.foreach(msg => {
            println(msg.value())
        })
        handleMessages(cons)
    }
    
    def kafkaConsume(topic: String) = {
        val props = new Properties()
        props.put("bootstrap.servers", "localhost:9092")
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        props.put("auto.offset.reset", "latest")
        props.put("group.id", "riot-alerts")
        
        val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
        consumer.subscribe(util.Arrays.asList(topic))
        
        handleMessages(consumer)
    }
}
