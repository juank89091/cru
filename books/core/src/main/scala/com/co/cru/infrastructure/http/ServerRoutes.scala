package com.co.cru.infrastructure.http

import akka.http.scaladsl.server.Directives.{extractRequest,_}
import com.co.cru.infrastructure.http.routes.CommandRoutes

trait ServerRoutes extends CommandRoutes with QuerieRoutes {

  def routes = extractRequest { req =>
    println(req.toString)
    commandRoutes ~ querieRoutes
  }

}
