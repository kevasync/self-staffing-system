package com.asynchrony.staffomatic.models.db

import org.squeryl.annotations.Column

class DbScore(@Column("SkillID") val skillId: Int,
              @Column("PersonID") val personId: Int,
              @Column("Score") val score: Option[Int],
              @Column("SkillYearsOfExperience") val yearsExperience: Option[Int]) {
  def this() = this(0, 0, None, None)
}
