package com.co

import cats.data.{EitherT, Kleisli}

import scala.concurrent.Future

package object cru {

  type ApiExecution[A, B] = Kleisli[ServiceResponse, A, B]
  type ServiceResponse[A] = EitherT[Future, String, A]

}
