package com.co.cru.infrastructure.transformers

import com.co.cru.domain.entities.Book
import com.co.cru.infrastructure.http.routes.lendbookRoute.BookDTO

trait DtoTransformerSyntax extends TransformerSyntax {
  def dto2Domain: Transformable[BookDTO, Book] =
    (b: BookDTO) => Book(b.name,b.ISBN, b.author, None)
}