import scala.math.{pow, sqrt}

object knn {
  def distance(x: Seq[Int], y: Seq[Int]): Double = sqrt(x.zip(y).map(n => pow(n._1 - n._2, 2)).sum)
}
