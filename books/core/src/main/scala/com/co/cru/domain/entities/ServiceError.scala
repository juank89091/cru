package com.co.cru.domain.entities

trait ServiceError {
  def code: String
  def message: String
}

case class InvalidStatus(code: String = "001", message: String) extends ServiceError

