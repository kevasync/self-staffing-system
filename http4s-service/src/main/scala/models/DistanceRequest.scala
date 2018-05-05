package models

case class DistanceRequest(a: Score, b: Score)
case class KnnRequest(score: Score)
case class KnnResponse(ids: Seq[String])

