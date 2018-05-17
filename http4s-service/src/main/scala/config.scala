import scala.util.Properties._

object config {
  val skillsDbUser: String = envOrElse("SKILLS_USER", "")
  val skillsDbPassword: String = envOrElse("SKILLS_PASSWORD", "")
}
