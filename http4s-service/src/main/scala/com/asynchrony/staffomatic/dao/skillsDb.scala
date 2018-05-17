package com.asynchrony.staffomatic.dao

import com.asynchrony.staffomatic.models.db.DbPerson
import org.squeryl.{Schema, Table}

object skillsDb extends Schema {
  val people: Table[DbPerson] = table("tblPeople")
}
