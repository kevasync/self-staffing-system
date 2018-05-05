import models._

case class Person(employeeId: String, team: String, scores: Seq[Double]){
  val individualScore = Score(employeeId, scores)
  val teamScore = Score(team, scores)
}

object skillsService {
  def trainingData() = Seq(
    Person("kev", "team1", SkillDto(java = 4, scala = 3, net = 2, js = 4, html = 1, ios = 0, android = 1).toScoreArray),
    Person("lou", "team2", SkillDto(java = 1, scala = 2, net = 2, js = 4, html = 1, ios = 0, android = 1).toScoreArray),
    Person("bob", "team3", SkillDto(java = 1, scala = 1, net = 5, js = 5, html = 5, ios = 0, android = 1).toScoreArray),
    Person("ted", "team3", SkillDto(java = 1, scala = 5, net = 2, js = 5, html = 5, ios = 0, android = 5).toScoreArray)
  )
}
