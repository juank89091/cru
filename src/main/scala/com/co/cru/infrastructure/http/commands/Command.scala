package com.co.cru.infrastructure.http.commands

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post, _}
import akka.http.scaladsl.server.Route
import cats.implicits._
import com.co.cru.ApiExecution
import com.co.cru.domain.AppConf
import com.co.cru.infrastructure.http.commands.lendbookcommand.Event
import com.co.cru.infrastructure.kafka.MessageProducer
import spray.json.RootJsonFormat

import scala.concurrent.ExecutionContext

trait Command[A] extends MessageProducer {

  def url: String

  def topic: String

  def conf: AppConf

  def bookRoute()(implicit format: RootJsonFormat[A], ec: ExecutionContext): Route =
    post {
      path(url) {
        entity(as[A]) { entity =>
          println("called")
          execute(entity).run(conf)
            .map(evento =>  writeToKafka(topic, evento.toString))
          complete("lent book")
        }
      }
    }

  def execute(a:A): ApiExecution[AppConf, Event]
}
