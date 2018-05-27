package com.asynchrony.staffomatic

import cats.effect.IO
import com.asynchrony.staffomatic.config.{skillsDbPassword, skillsDbUrl, skillsDbUser}
import org.http4s.server.Server
import org.http4s.server.blaze._
import org.squeryl.adapters.MSSQLServer
import org.squeryl.{Session, SessionFactory}
import org.http4s.server.middleware._

object main extends App {
  val builder = BlazeBuilder[IO].bindHttp(8081, "localhost")
    .mountService(CORS(routes.yo), "/test")
    .mountService(CORS(routes.knn), "/")
    .mountService(CORS(routes.skillsDb), "/skills")
    .start

  var server: Server[IO] = _
  sys.ShutdownHookThread {
    server.shutdown.unsafeRunSync()
    println("dying like i should, you can have my stuff")
  }

  SessionFactory.concreteFactory = Some(() => Session.create(
    java.sql.DriverManager.getConnection(s"jdbc:jtds:sqlserver://$skillsDbUrl", skillsDbUser, skillsDbPassword),
    new MSSQLServer))

  server = builder.unsafeRunSync()
}
