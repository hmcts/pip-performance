package requests.account

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import utils.auth.OAuthAPI.config
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.collection.mutable.ListBuffer

object AccountRequests {

  val mapper = new ObjectMapper();
  mapper.registerModule(DefaultScalaModule)

  // Set paths for endpoints
  private val AccountsPath = config.accountManagementApi.url + "/account/add/pi"
  private val TestingSupportDeleteAccountPath = config.accountManagementApi.url + "/testing-support/account/"

  //Create base setup for Accounts
  val httpProtocol = http.baseUrl(config.accountManagementApi.url)

  // Set requests
  def createAccount(accounts: ListBuffer[Map[String, String]]): HttpRequestBuilder = http("Create a PI_AAD Account")
    .post(AccountsPath)
    .body(ByteArrayBody(mapper.writeValueAsBytes(accounts)))
    .header("Authorization", "bearer ${bearerx}")
    .header("x-issuer-id", config.testSystemAdminId)
    .header("Content-Type", "application/json")
    .check(status is 201)
    .check(bodyString.saveAs("ResponseBody"))
    .check(jsonPath("$['CREATED_ACCOUNTS']").ofType[Seq[Any]].find.saveAs("createdAccountIds"))

  def deleteAccounts(emailPrefix: String): HttpRequestBuilder = http("Delete all accounts with prefix")
    .delete(TestingSupportDeleteAccountPath + emailPrefix)
    .header("Authorization", "bearer ${bearerx}")
    .check(status is 200)

}