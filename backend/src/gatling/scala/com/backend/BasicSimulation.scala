import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {
  val httpProtocol = http.baseUrl("http://host.docker.internal:8080")
    .acceptHeader("application/json")

  val scn = scenario("API Basic Load")
    .exec(http("List Alunos")
      .get("/api/alunos")
      .check(status.is(200)))
    .pause(1)

  setUp(
    scn.inject(
      rampUsers(200) during (60.seconds)
    ).protocols(httpProtocol)
  )
}
