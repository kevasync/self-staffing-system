package com.asynchrony.staffomatic.models.db

import org.squeryl.annotations.Column

class DbPerson(@Column("PersonID") val id: Int,
               @Column("FirstName") val firstName: String,
               @Column("LastName") val lastName: String) {
  def this() = this(0, "", "")
}
