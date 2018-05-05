import models.Score
import scala.math.{pow, sqrt}

object knn {
  def topN(ref: Score, trainingData: Seq[Score], n: Int = 5) =
    trainingData.par.map(x => x -> distance(ref.scores, x.scores)).toList
    .sortWith((x, y) => x._2 < y._2)
    .take(n)

  //https://gist.github.com/hanbei/6357444
  def distance(x: Seq[Int], y: Seq[Int]): Double =
    sqrt(x.zip(y).map(n => pow(n._1 - n._2, 2)).sum)
}
