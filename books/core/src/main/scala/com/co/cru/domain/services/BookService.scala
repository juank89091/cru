package com.co.cru.domain.services

import cats.data.{EitherT, Kleisli}
import com.co.cru.domain.BooksConf
import com.co.cru.domain.entities.{Book, Lent}
import com.co.cru.{ApiExecution, ServiceResponse}
import com.softwaremill.quicklens._

import scala.concurrent.Future

trait BookService {

  def lendBook(book: Book): ApiExecution[BooksConf, Book] = Kleisli {
    conf =>
     EitherT[Future, String, Book](Future.successful(Right(book.modify(_.status).setTo(Option(Lent))))):ServiceResponse[Book]
  }
}

object bookService extends BookService
