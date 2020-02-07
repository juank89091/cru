package com.co.cru

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.co.cru.domain.AppConf
import com.co.cru.infrastructure.http.ServerRoutes

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContextExecutor}
import scala.util.{Failure, Success}

object Main extends App with ServerRoutes {

  implicit val system: ActorSystem                        = ActorSystem("inventory")
  implicit val materializer: ActorMaterializer            = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val server = Http().bindAndHandle(routes, "localhost", 9000)

  server.onComplete {
    case Success(Http.ServerBinding(localAddress)) =>
      println(s"Server online at ${localAddress.getAddress}:${localAddress.getPort}")
    case Failure(ex) =>
      println(s"There was an error while starting server, exception was ${ex.getMessage}")
      ex.getStackTrace.foreach(println)
  }

  sys.addShutdownHook {
    Await.ready(system.terminate, 10.seconds)
    ()
  }

}
