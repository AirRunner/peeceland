import java.util
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer
import Mongo.insertReport

object Consumer {
    def handleMessages(cons: KafkaConsumer[String, String]): Unit = {
        val record = cons.poll(1000).asScala
        record.iterator.foreach(msg => {
            insertReport(msg.value())
        })
        handleMessages(cons)
    }
    
    def kafkaConsume(topic: String) = {
        val props = new Properties()
        props.put("bootstrap.servers", "localhost:9092")
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        props.put("auto.offset.reset", "latest")
        props.put("group.id", "drone-reports")
        
        val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
        consumer.subscribe(util.Arrays.asList(topic))
        
        handleMessages(consumer)
    }
}
