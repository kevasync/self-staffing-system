package com.asynchrony.staffomatic

import cats.effect._
import com.asynchrony.staffomatic.dao.personDao
import com.asynchrony.staffomatic.models.{DistanceRequest, KnnRequest, KnnResponse}
import com.asynchrony.staffomatic.services.{knnService, skillsService}
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._

object routes {
  val yo = HttpService[IO] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hola, $name")
  }

  implicit val distanceReqDecoder: EntityDecoder[IO, DistanceRequest] = jsonOf
  implicit val knnReqDecoder: EntityDecoder[IO, KnnRequest] = jsonOf

  private val defaultNumberOfSuggestions = 2

  val knn = HttpService[IO] {
    case req@POST -> Root / "distance" =>
      req.decode[DistanceRequest] { r =>
        Ok(knnService.distance(r.a.scores, r.b.scores).toString)
      }
    case req@POST -> Root / "knn" =>
      req.decode[KnnRequest] { r =>
        Ok(KnnResponse(
          knnService.topN(r.score, skillsService.trainingData().map(_.individualScore), defaultNumberOfSuggestions).map(_._1.id)
        ).asJson)
      }
    case req@POST -> Root / "knnByTeam" =>
      req.decode[KnnRequest] { r =>
        val scoresByTeam = skillsService.trainingData().map(_.teamScore).groupBy(_.id)
          .map(x => x._1 -> x._2.map(_.scores)).toSeq

        Ok(KnnResponse(
          knnService.topNCategorized(r.score, scoresByTeam, defaultNumberOfSuggestions).map(_._1.id)).asJson
        )
      }
  }

  val skillsDb = HttpService[IO] {
    case GET -> Root / "people"  =>
      Ok(personDao.get.asJson)
  }
}
