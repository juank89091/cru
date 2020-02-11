package com.co.cru.infrastructure.transformers

import com.co.cru.domain.entities.Book
import com.co.cru.message.BookStatusAltered

trait ProtobufTransformersSyntax extends TransformerSyntax {
  def bookToBookStatusAltered: Transformable[Book, BookStatusAltered] =
    (book: Book) =>
      BookStatusAltered(book.name,book.ISBN, book.author, book.status.map(_.toString).getOrElse("Available"))

}
