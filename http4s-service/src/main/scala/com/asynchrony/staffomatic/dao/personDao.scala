package com.asynchrony.staffomatic.dao

import com.asynchrony.staffomatic.models.db.DbPerson
import org.squeryl.PrimitiveTypeMode._

object personDao {
  def get: Seq[DbPerson] = inTransaction(skillsDb.people.toList)
}
