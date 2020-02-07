package com.co.cru.infrastructure.http.commands.lendbookcommand

trait Event
case class BookStatusAltered (name: String,
                              ISBN: String,
                              author: String,
                              status: String) extends Event
