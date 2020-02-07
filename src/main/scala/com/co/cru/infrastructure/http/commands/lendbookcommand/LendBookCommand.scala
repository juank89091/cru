package com.co.cru.infrastructure.http.commands.lendbookcommand

import cats.data.Kleisli
import cats.implicits._
import com.co.cru.domain.AppConf
import com.co.cru.domain.entities.Book
import com.co.cru.domain.services.bookService
import com.co.cru.infrastructure.http.commands.Command
import com.co.cru.{ApiExecution, ServiceResponse}

import scala.concurrent.ExecutionContext.Implicits.global


trait LendBookCommand extends Command[BookDTO] {

  val conf = new AppConf {}

  def url = "lend"

  def topic = "BookStatusAltered"

  def execute(bookDTO: BookDTO): ApiExecution[AppConf, Event] = Kleisli {
    conf =>
     val book = Book(bookDTO.name,bookDTO.ISBN, bookDTO.author, None)
     bookService.lendBook(book).run(conf)
        .map(book =>
          BookStatusAltered(book.name,book.ISBN, book.author, book.status.map(_.toString).getOrElse("Available"))
        ):ServiceResponse[Event]
  }
}

object lendBookComand extends LendBookCommand
