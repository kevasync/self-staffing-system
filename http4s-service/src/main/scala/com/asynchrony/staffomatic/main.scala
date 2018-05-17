package com.asynchrony.staffomatic

import cats.effect.IO
import com.asynchrony.staffomatic.config.{skillsDbPassword, skillsDbUrl, skillsDbUser}
import org.http4s.server.Server
import org.http4s.server.blaze._
import org.squeryl.adapters.MSSQLServer
import org.squeryl.{Session, SessionFactory}


object main extends App {
  val builder = BlazeBuilder[IO].bindHttp(8080, "localhost")
    .mountService(routes.yo, "/test")
    .mountService(routes.knn, "/")
    .mountService(routes.skillsDb, "/skills")
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
