package scenarios.data

import io.gatling.core.Predef._
import requests.data.UploadHtmlFileRequest
import requests.data.PublicationRequests.{courtListFeed, createDifferentSizePublicationFeed, createPublicationFeed}
import utils.auth.OAuthAPI
import utils.auth.OAuthAPI.config

object UploadHtmlFileScenario {

  val UploadHtmlPublicationScenario = scenario("Create HTML Publication scenario")
    .exec(OAuthAPI.authData)
    .feed(createDifferentSizePublicationFeed)
    .feed(courtListFeed)
    .exec(session => session.set("requesterId", config.testSystemAdminId))
    .exec(UploadHtmlFileRequest.uploadHtmlPublicationRequest)
}
