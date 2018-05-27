package com.asynchrony.staffomatic.models.db

import org.squeryl.annotations.Column

class DbSkill(@Column("SkillID") val id: Int,
               @Column("Skill") val skill: String,
               @Column("CategoryID") val category: String) {
  def this() = this(0, "", "")
}
