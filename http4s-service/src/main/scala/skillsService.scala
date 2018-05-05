import models._


object skillsService {
  def trainingData() = Seq(
    Score("kev", SkillDto(java = 4, scala = 3, net = 2, js = 4, html = 1, ios = 0, android = 1).toScoreArray),
    Score("bob", SkillDto(java = 1, scala = 1, net = 5, js = 5, html = 5, ios = 0, android = 1).toScoreArray),
    Score("ted", SkillDto(java = 1, scala = 5, net = 2, js = 5, html = 0, ios = 0, android = 5).toScoreArray)
  )
}
