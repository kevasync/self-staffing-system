package com.asynchrony.staffomatic

import scala.util.Properties._

object config {
  val skillsDbUser: String = envOrElse("SKILLS_USER", "")
  val skillsDbPassword: String = envOrElse("SKILLS_PASSWORD", "")
  val skillsDbUrl: String = envOrElse("SKILLS_URL", "skills-demo.asynchrony.com:1433/SkillsDBProWWT")
}
