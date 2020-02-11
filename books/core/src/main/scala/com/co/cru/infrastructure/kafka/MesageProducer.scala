package com.co.cru.infrastructure.kafka

import java.util.Properties

import akka.Done
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.concurrent.{ExecutionContext, Future}

trait MessageProducer {

  def writeToKafka(topic: String, value:Array[Byte])(implicit ec: ExecutionContext) = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer")
    val producer = new KafkaProducer[String,Array[Byte]](props)
    val record = new ProducerRecord[String, Array[Byte]](topic, "key", value)
    Future{
      producer.send(record)
      producer.close()
      Done
    }
  }
}