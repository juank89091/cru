package com.co.cru.infrastructure.transformers

trait TransformerSyntax {
  implicit class TransformerOps[A](value: A) {
    def transform[E](implicit transformer: Transformable[A, E]): E = transformer.transform(value)
  }
}
