import cats.effect._
import io.circe.generic.auto._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._

case class Score(employeeId: String, scores: Seq[Int])
case class DistanceRequest(a: Score, b: Score)

object services {
  val yoService = HttpService[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hola, $name")
  }

  implicit val distanceReqDecoder: EntityDecoder[IO, DistanceRequest] = jsonOf[IO, DistanceRequest]
  val distanceService = HttpService[IO] {
    case req @ POST -> Root / "distance" =>
      req.decode[DistanceRequest] { r =>
        Ok(knn.distance(r.a.scores, r.b.scores).toString)
      }
  }

}
