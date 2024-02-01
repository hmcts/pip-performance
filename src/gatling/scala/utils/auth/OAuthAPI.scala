package utils.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import config.RootConfig
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.apache.commons.text.StringSubstitutor

import java.io.FileReader
import java.nio.file.{Files, Paths}

object OAuthAPI {

  val reader = new FileReader("src/gatling/resources/application.yaml")

  val interpolator: StringSubstitutor = StringSubstitutor.createInterpolator
  interpolator.setEnableSubstitutionInVariables(true)
  val text: String = interpolator.replace(Files.readString(Paths.get("src/gatling/resources/application.yaml")))

  val mapper = new ObjectMapper(new YAMLFactory())
  val config: RootConfig = mapper.readValue(text, classOf[RootConfig])

  private val tenant = config.tenantId;
  private val clientId = config.clientId;
  private val clientSecret = config.clientSecret;
  private val clientIdAccManagement = config.clientIdAccManagement;
  private val clientSecretAccManagement = config.clientSecretAccManagement;
  private val scope = config.dataManagementApi.scope;
  private val scopeChannel = config.channelManagementApi.scope;
  private val scopePublication = config.publicationServicesApi.scope;
  private val grantType = "client_credentials";

  val header = Map("Content-Type" -> """application/x-www-form-urlencoded""");
  val authURI = "https://login.microsoftonline.com";

  val authData = scenario("GetToken")
    .exec(http("Microsoft Token Generation")
      .post(s"$authURI/$tenant/oauth2/v2.0/token")
      .formParam("scope", scope)
      .formParam("grant_type", grantType)
      .formParam("client_secret", clientSecret)
      .formParam("client_id", clientId)
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearerx")))

  val authChannel = scenario("GetToken")
    .exec(http("Microsoft Token Generation")
      .post(s"$authURI/$tenant/oauth2/v2.0/token")
      .formParam("scope", scopeChannel)
      .formParam("grant_type", grantType)
      .formParam("client_secret", clientSecret)
      .formParam("client_id", clientId)
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearerx")))

  val authPublication = scenario("GetToken")
    .exec(http("Microsoft Token Generation")
      .post(s"$authURI/$tenant/oauth2/v2.0/token")
      .formParam("scope", scopePublication)
      .formParam("grant_type", grantType)
      .formParam("client_secret", clientSecretAccManagement)
      .formParam("client_id", clientIdAccManagement)
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearerx")))
}
