package com.co.cru.infrastructure.http.routes.lendbookRoute

import cats.data.Kleisli
import com.co.cru.ApiExecution
import com.co.cru.domain.BooksConf
import com.co.cru.domain.commands.LendBookCommand
import com.co.cru.infrastructure.http.routes.Router
import com.co.cru.infrastructure.transformers.transformers._
import scalapb.GeneratedMessage


trait LendBookRoute extends Router[BookDTO] {

  val conf = new BooksConf {}

  def url = "lend"

  def topic = "BookStatusAltered"

  def execute(bookDTO: BookDTO): ApiExecution[BooksConf, GeneratedMessage] = Kleisli {
    conf =>
     val book = DtoTransformers.dto2Domain.transform(bookDTO)
     LendBookCommand(book).execute().run(conf)
  }
}

object lendBookRoute extends LendBookRoute
