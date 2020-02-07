package com.co.cru.domain.entities

case class Book (name: String,
                 ISBN: String,
                 author: String,
                 status: Option[BookStatus])
