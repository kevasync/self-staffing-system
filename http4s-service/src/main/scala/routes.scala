import cats.effect._
import io.circe.generic.auto._
import models.{DistanceRequest, KnnRequest, KnnResponse}
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import io.circe.syntax._

object routes {
  val yoService = HttpService[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hola, $name")
  }

  implicit val distanceReqDecoder: EntityDecoder[IO, DistanceRequest] = jsonOf
  implicit val knnReqDecoder: EntityDecoder[IO, KnnRequest] = jsonOf

  val knnService = HttpService[IO] {
    case req@POST -> Root / "distance" =>
      req.decode[DistanceRequest] { r =>
        Ok(knn.distance(r.a.scores, r.b.scores).toString)
      }
    case req@POST -> Root / "knn" =>
      req.decode[KnnRequest] { r =>
        Ok(KnnResponse(knn.topN(r.score, skillsService.trainingData(), 2).map(_._1.employeeId)).asJson)
      }
  }
}
