import cats.effect.IO
import org.http4s.server.Server
import org.http4s.server.blaze._
import org.squeryl.adapters.MSSQLServer
import org.squeryl.annotations.Column
import org.squeryl.{Schema, Session, SessionFactory, Table}
import org.squeryl.PrimitiveTypeMode._

class DbPerson(@Column("PersonID") val id: Int,
               @Column("FirstName") val firstName: String,
               @Column("LastName") val lastName: String) {
  def this() = this(0, "", "")
}

object db extends Schema {
  val people: Table[DbPerson] = table("tblPeople")
}

object main extends App {
  val builder = BlazeBuilder[IO].bindHttp(8080, "localhost")
    .mountService(routes.yoService, "/test")
    .mountService(routes.knnService, "/")
    .start

  var server: Server[IO] = _
  sys.ShutdownHookThread {
    server.shutdown.unsafeRunSync()
    println("dying like i should, you can have my stuff")
  }

  SessionFactory.concreteFactory = Some(() => Session.create(
      java.sql.DriverManager.getConnection(
        "jdbc:jtds:sqlserver://skills-demo.asynchrony.com:1433/SkillsDBProWWT",
        config.skillsDbUser, config.skillsDbPassword),
      new MSSQLServer))

  val ppl = inTransaction(db.people.toList)
  ppl.foreach(x => println(s"${x.firstName} ${x.lastName}"))

  server = builder.unsafeRunSync()
}
