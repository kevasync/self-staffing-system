import cats.effect._
import io.circe.generic.auto._
import io.circe.syntax._
import models.{DistanceRequest, KnnRequest, KnnResponse}
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._

object routes {
  val yoService = HttpService[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hola, $name")
  }

  implicit val distanceReqDecoder: EntityDecoder[IO, DistanceRequest] = jsonOf
  implicit val knnReqDecoder: EntityDecoder[IO, KnnRequest] = jsonOf

  private val defaultNumberOfSuggestions = 5

  val knnService = HttpService[IO] {
    case req@POST -> Root / "distance" =>
      req.decode[DistanceRequest] { r =>
        Ok(knn.distance(r.a.scores, r.b.scores).toString)
      }
    case req@POST -> Root / "knn" =>
      req.decode[KnnRequest] { r =>
        Ok(KnnResponse(
          knn.topN(r.score, skillsService.trainingData().map(_.individualScore), defaultNumberOfSuggestions)
            .map(_._1.id)
        ).asJson)
      }
    case req@POST -> Root / "knnByTeam" =>
      req.decode[KnnRequest] { r =>
        val scoresByTeam = skillsService.trainingData().map(_.teamScore).groupBy(_.id)
          .map(x => x._1 -> x._2.map(_.scores)).toSeq

        Ok(KnnResponse(
          knn.topNCategorized(r.score, scoresByTeam, defaultNumberOfSuggestions).map(_._1.id)).asJson
        )
      }
  }
}
