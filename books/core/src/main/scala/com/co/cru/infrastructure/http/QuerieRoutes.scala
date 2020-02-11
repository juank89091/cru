package com.co.cru.infrastructure.http

import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route

trait QuerieRoutes {

  def querieRoutes = healthRoute

  def healthRoute: Route = path("health") {
    get {
      complete(OK -> "OK")
    }
  }
}
