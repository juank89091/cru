package com.co.cru.infrastructure.http

import akka.http.scaladsl.server.Directives.{extractRequest, _}
import com.co.cru.infrastructure.http.commands.Commands

trait ServerRoutes extends Commands with Queries {

  def routes = extractRequest { req =>
    println(req.toString)
    commandRoutes ~ querieRoutes
  }

}
