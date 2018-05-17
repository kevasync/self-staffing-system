import models.Score
import scala.math.{pow, sqrt}

object knn {
  def topN(ref: Score, trainingData: Seq[Score], n: Int): Seq[(Score, Double)] =
    trainingData.par.map(x => x -> distance(ref.scores, x.scores)).toList
      .sortWith((x, y) => x._2 < y._2)
      .take(n)

  def topNCategorized(ref: Score, groupedScores: Seq[(String, Seq[Seq[Double]])], n: Int): Seq[(Score, Double)] = {
    def avg(n: Seq[Seq[Double]]) = n.head.indices.foldLeft
      [Seq[Double]](Nil)((acc, i) =>
      acc :+ n.map(_(i)).sum / n.length)

    val averagedCategoryScores = groupedScores.map(x => Score(x._1, avg(x._2)))
    topN(ref, averagedCategoryScores, n)
  }

  //https://gist.github.com/hanbei/6357444
  def distance(x: Seq[Double], y: Seq[Double]): Double =
    sqrt(x.zip(y).map(n => pow(n._1 - n._2, 2)).sum)
}
