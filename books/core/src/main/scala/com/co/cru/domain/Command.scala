package com.co.cru.domain

import com.co.cru.ApiExecution
import scalapb.GeneratedMessage

trait Command {

  def execute(): ApiExecution[BooksConf, GeneratedMessage]

}
