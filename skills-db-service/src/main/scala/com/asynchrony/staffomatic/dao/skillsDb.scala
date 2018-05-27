package com.asynchrony.staffomatic.dao

import com.asynchrony.staffomatic.models.db._
import org.squeryl.{Schema, Table}

object skillsDb extends Schema {
  val people: Table[DbPerson] = table("tblPeople")
  val skills: Table[DbSkill] = table("tblSkills")
  val scores: Table[DbScore] = table("tblScores")
}
