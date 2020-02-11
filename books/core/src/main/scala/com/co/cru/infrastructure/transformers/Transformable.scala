package com.co.cru.infrastructure.transformers

trait Transformable[A, E] {
  def transform(value: A): E
}
