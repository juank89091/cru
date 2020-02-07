package com.co.cru.infrastructure.http.commands

import com.co.cru.infrastructure.http.commands.lendbookcommand.{BookDTO, lendBookComand}
import spray.json.DefaultJsonProtocol.jsonFormat3
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._

import scala.concurrent.ExecutionContext
trait Commands {

 implicit val ec = ExecutionContext.global

 implicit val bookFormat: RootJsonFormat[lendbookcommand.BookDTO] = jsonFormat3(BookDTO)

 def commandRoutes = lendBookComand.bookRoute()

}
