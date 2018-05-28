package com.asynchrony.staffomatic.services

import com.asynchrony.staffomatic.dao.skillsDao
import com.asynchrony.staffomatic.dao.skillsDb._
import com.asynchrony.staffomatic.models.db.{DbPerson, DbScore}
import com.asynchrony.staffomatic.models.{Score, SuggestionRequest}

import scala.math.{pow, sqrt}

object knnService {
  def suggest(suggestionRequest: SuggestionRequest, numberToReturn: Int = 5): Seq[DbPerson] = {
    val maxScore = 5.0
    val referenceScore = Score("", Array.fill(suggestionRequest.skillIds.length)(maxScore))
    val relevantScores = skillsDao.get(scores).filter(score => suggestionRequest.skillIds.contains(score.skillId))
    val distinctPersonIds = relevantScores.map(_.personId).distinct
    val normalizedScoresByPerson = distinctPersonIds.map(personId => {
      val personScores = relevantScores.filter(score => score.personId == personId)
      Score(
        id = personId.toString,
        scores = suggestionRequest.skillIds.foldLeft
          [Seq[Double]](Nil)((acc, skillId) =>
            acc :+ personScores.find(score => score.skillId == skillId)
              .getOrElse(DbScore(skillId, personId))
              .score.getOrElse(0)
              .toDouble))
    })

    val winnerPersonIds = topN(referenceScore, normalizedScoresByPerson, numberToReturn).map(_._1.id)

    skillsDao.get(people)
      .filter(person => winnerPersonIds.contains(person.id.toString))
      .sortBy(person => winnerPersonIds.indexOf(person.id.toString))
  }

  def topN(ref: Score, trainingData: Seq[Score], n: Int): Seq[(Score, Double)] =
    trainingData.par.map(x => x -> distance(ref.scores, x.scores)).toList
      .sortWith((x, y) => x._2 < y._2)
      .take(n)

  //https://gist.github.com/hanbei/6357444
  def distance(x: Seq[Double], y: Seq[Double]): Double =
    sqrt(x.zip(y).map(n => pow(n._1 - n._2, 2)).sum)

  def topNCategorized(ref: Score, groupedScores: Seq[(String, Seq[Seq[Double]])], n: Int): Seq[(Score, Double)] = {
    def avg(n: Seq[Seq[Double]]) = n.head.indices.foldLeft
      [Seq[Double]](Nil)((acc, i) =>
        acc :+ n.map(_ (i)).sum / n.length)

    val averagedCategoryScores = groupedScores.map(x => Score(x._1, avg(x._2)))
    topN(ref, averagedCategoryScores, n)
  }
}
