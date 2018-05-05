package models

case class SkillDto(java: Int, scala: Int, net: Int, js: Int, html: Int, ios: Int, android: Int) {
  def toScoreArray = Seq(java, scala, net, js, html, ios, android)
}