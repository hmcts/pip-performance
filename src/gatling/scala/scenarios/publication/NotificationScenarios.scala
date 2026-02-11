package scenarios.publication

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import requests.publication.NotificationRequests
import scala.util.Random

object NotificationScenarios {

  private val TestEmailPrefix = s"pip-local-perf-test-email-prefix-${Random.between(10000, 100000)}@justice.gov.uk"

  def notifySubscriptionTwoCasesFlow(artefactId: String, locationId: Int): ChainBuilder = {
    val notifySubscriptionBodyTwoCases = s"""{"artefactId":"$artefactId","subscriptionEmails":[{"email":"$TestEmailPrefix","subscriptions":{"LOCATION_ID":["$locationId"]}}]}"""
    exec(NotificationRequests.postNotifySubscription(notifySubscriptionBodyTwoCases))
  }

  def notifySubscriptionFiftyCasesFlow(artefactId: String, locationId: Int): ChainBuilder = {
    val notifySubscriptionBodyFiftyCases = s"""{"artefactId":"$artefactId","subscriptionEmails":[{"email":"$TestEmailPrefix","subscriptions":{"LOCATION_ID":["$locationId"]}}]}"""
    exec(NotificationRequests.postNotifySubscription(notifySubscriptionBodyFiftyCases))
  }

  def notifySubscriptionHundredCasesFlow(artefactId: String, locationId: Int): ChainBuilder = {
    val notifySubscriptionBodyHundredCases = s"""{"artefactId":"$artefactId","subscriptionEmails":[{"email":"$TestEmailPrefix","subscriptions":{"LOCATION_ID":["$locationId"]}}]}"""
    exec(NotificationRequests.postNotifySubscription(notifySubscriptionBodyHundredCases))
  }

  def notifySubscriptionTwoHundredCasesFlow(artefactId: String, locationId: Int): ChainBuilder = {
    val notifySubscriptionBodyTwoHundredCases = s"""{"artefactId":"$artefactId","subscriptionEmails":[{"email":"$TestEmailPrefix","subscriptions":{"LOCATION_ID":["$locationId"]}}]}"""
    exec(NotificationRequests.postNotifySubscription(notifySubscriptionBodyTwoHundredCases))
  }
}