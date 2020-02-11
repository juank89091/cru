package com.co.cru.infrastructure.http.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post, _}
import akka.http.scaladsl.server.Route
import cats.implicits._
import com.co.cru.ApiExecution
import com.co.cru.domain.BooksConf
import com.co.cru.infrastructure.kafka.MessageProducer
import scalapb.GeneratedMessage
import spray.json.RootJsonFormat

import scala.concurrent.ExecutionContext

trait Router[A] extends MessageProducer {

  def url: String

  def topic: String

  def conf: BooksConf

  def bookRoute()(implicit format: RootJsonFormat[A], ec: ExecutionContext): Route =
    post {
      path(url) {
        entity(as[A]) { entity =>
          println("called")
          execute(entity).run(conf)
            .map(evento =>  writeToKafka(topic, evento.toByteArray))
          complete("lent book")
        }
      }
    }

  def execute(a:A): ApiExecution[BooksConf, GeneratedMessage]
}
