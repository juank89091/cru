package com.co.cru.domain.commands

import cats.data.Kleisli
import cats.implicits._
import com.co.cru.domain.entities.Book
import com.co.cru.domain.{BooksConf, Command}
import com.co.cru.domain.services.bookService
import com.co.cru.infrastructure.http.routes.Router
import com.co.cru.infrastructure.transformers.transformers._
import com.co.cru.{ApiExecution, ServiceResponse}
import scalapb.GeneratedMessage

import scala.concurrent.ExecutionContext.Implicits.global


case class LendBookCommand(book: Book) extends Command {

  override def execute(): ApiExecution[BooksConf, GeneratedMessage] = Kleisli {
    conf =>
      bookService.lendBook(book).run(conf)
        .map(book =>
          ProtobufTransformers.bookToBookStatusAltered.transform(book)
        ):ServiceResponse[GeneratedMessage]
  }
}