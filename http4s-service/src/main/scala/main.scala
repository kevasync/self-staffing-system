import cats.effect.IO
import org.http4s.server.Server
import org.http4s.server.blaze._

object main extends App {
  val builder = BlazeBuilder[IO].bindHttp(8080, "localhost")
    .mountService(routes.yoService, "/test")
    .mountService(routes.distanceService, "/")
    .start

  var server: Server[IO] = _
  sys.ShutdownHookThread {
    server.shutdown.unsafeRunSync()
    println("dying like i should, you can have my stuff")
  }

  server = builder.unsafeRunSync()
}
