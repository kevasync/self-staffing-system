package com.asynchrony.staffomatic.dao

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Table

object skillsDao {
  def get[T](t: Table[T]) = inTransaction(t.toList)
}
