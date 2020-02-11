package com.co.cru.domain.entities

import cats.data.Validated
import cats.syntax.validated._

sealed trait BookStatus
case object Lent  extends BookStatus
case object Available  extends BookStatus

object BookStatus {
  def apply(string: String): Validated[ServiceError, BookStatus] = string.toLowerCase.trim match {
    case "lent"   => Lent.valid
    case "available"  => Available.valid
    case v           => InvalidStatus(message = s"'$v' is not a valid book status").invalid
  }
}