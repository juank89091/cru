package com.co.cru.infrastructure.http.routes

import com.co.cru.infrastructure.http.routes.lendbookRoute.{BookDTO,_}
import spray.json.DefaultJsonProtocol.jsonFormat3
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._

import scala.concurrent.ExecutionContext
trait CommandRoutes {

 implicit val ec = ExecutionContext.global

 implicit val bookFormat: RootJsonFormat[lendbookRoute.BookDTO] = jsonFormat3(BookDTO)

 def commandRoutes = lendBookRoute.bookRoute()

}
