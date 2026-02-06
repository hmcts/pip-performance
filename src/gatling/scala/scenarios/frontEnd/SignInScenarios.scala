package scenarios.frontEnd

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utils.auth.OAuthAPI.config

object SignInScenarios {

  val baseUrl = config.frontEndUrl
  val idamUrl = config.idamUrl

  val username = config.testCrimeUserId
  val password = config.testCrimeUserPassword

  val commonPlatformLogin = exec(
    http("GET Sign-In Page")
      .get(s"$baseUrl/sign-in")
      .check(status.is(200))
  )
    .pause(1)
    .exec(
      http("POST Select Common Platform Account")
        .post(s"$baseUrl/sign-in")
        .formParam("sign-in", "common")
        .disableFollowRedirect
        .check(status.is(302))
        .check(header("Location").saveAs("crimeLoginPath"))
    )
    .pause(1)
    .exec(
      http("GET Crime Login Redirect")
        .get(s"$baseUrl#{crimeLoginPath}")
        .disableFollowRedirect
        .check(status.is(302))
        .check(header("Location").saveAs("idamOAuthUrl"))
    )
    .pause(1)
    .exec(
      http("GET IDAM OAuth Authorize")
        .get("#{idamOAuthUrl}")
        .disableFollowRedirect
        .check(status.is(302))
        .check(header("Location").saveAs("idamLoginUrl"))
    )
    .pause(1)
    .exec(
      http("GET IDAM Login Page")
        .get("#{idamLoginUrl}")
        .disableFollowRedirect
        .check(status.is(302))
        .check(header("Location").saveAs("idamXuiPath"))
    )
    .pause(1)
    .exec(
      http("GET IDAM XUI Login")
        .get(s"$idamUrl#{idamXuiPath}")
        .check(status.is(200))
    )
    .pause(1)
    .exec(
      http("POST IDAM Authenticate Init")
        .post(s"$idamUrl/idp/json/realms/root/realms/idamidp/authenticate")
        .header("Content-Type", "application/json")
        .header("Accept-API-Version", "protocol=1.0,resource=2.1")
        .header("X-Requested-With", "XMLHttpRequest")
        .body(StringBody("{}"))
        .check(status.is(200))
        .check(jsonPath("$.authId").saveAs("authId"))
    )
    .pause(1)
    .exec(
      http("POST IDAM Login Credentials")
        .post(s"$idamUrl/idp/json/realms/root/realms/idamidp/authenticate")
        .header("Content-Type", "application/json")
        .header("Accept-API-Version", "protocol=1.0,resource=2.1")
        .header("X-Requested-With", "XMLHttpRequest")
        .body(StringBody(session => {
          val authId = session("authId").as[String]
          s"""{"authId":"$authId","callbacks":[{"type":"NameCallback","output":[{"name":"prompt","value":"User Name"}],"input":[{"name":"IDToken1","value":"$username"}]},{"type":"PasswordCallback","output":[{"name":"prompt","value":"Password"}],"input":[{"name":"IDToken2","value":"$password"}]}]}"""
        }))
        .check(status.is(200))
    )
    .pause(1)
    .exec(
      http("GET OAuth Authorize After Login")
        .get("#{idamOAuthUrl}")
        .disableFollowRedirect
        .check(status.is(302))
        .check(header("Location").saveAs("oauthCallbackUrl"))
    )
    .pause(1)
    .exec(
      http("Complete OAuth Callback")
        .get("#{oauthCallbackUrl}")
        .check(status.is(200))
        .check(substring("Your account"))
    )

  val logout = exec(
    http("GET Logout")
      .get(s"$baseUrl/logout")
      .check(status.in(200, 302))
  )
}
