package com.co.cru.infrastructure.kafka

import java.util.Properties

import com.co.cru.domain.BooksConf
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}

import scala.collection.JavaConverters._

trait MessageConsumer {

  def conf: BooksConf

  def props: Properties

  def topics: List[String]

  val consumer: KafkaConsumer[String, Array[Byte]] = new KafkaConsumer[String, Array[Byte]](props)
  consumer.subscribe(topics.asJavaCollection)
  while (true) {
    val record = consumer.poll(1000).asScala
    for (evento <- record.iterator) {
     execute(evento)//.run(conf)
     // println(evento.topic())
    }
  }

  def execute(evento: ConsumerRecord[String, Array[Byte]]): Unit // ApiExecution[BooksConf, GeneratedMessage]

}
