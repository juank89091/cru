import java.util
import java.util.Properties
import java.util.concurrent.Future

import cats.data.Kleisli
import com.co.cru.ApiExecution
import com.co.cru.domain.BooksConf
import com.co.cru.infrastructure.kafka.MessageConsumer
import com.co.cru.message.BookStatusAltered
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}
import scalapb.GeneratedMessage

import scala.collection.JavaConverters._

object Main extends App with MessageConsumer {

  override def conf: BooksConf = new BooksConf {}

  override def props: Properties = {
    val properties = new Properties()
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    properties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer")
    properties.put("auto.offset.reset", "latest")
    properties.put("group.id", "consumer-group")
    properties
  }

  def topics: List[String] = List(
    "BookStatusAltered",
    "BookStatusMastered"
  )

  override def execute(evento: ConsumerRecord[String, Array[Byte]]): Unit /*ApiExecution[BooksConf, GeneratedMessage] = Kleisli*/ = {
    //conf =>
      evento.topic() match {
        case "BookStatusAltered" => println("procesando evento BookStatusAltered")
        case "BookStatusMastered" => println("procesando evento BookStatusMastered")
        case _ => println("no se puede procesar el evento BookStatusMastered")
      }
  }


}
