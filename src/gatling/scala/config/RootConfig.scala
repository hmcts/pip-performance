package config

import com.fasterxml.jackson.annotation.JsonProperty

class RootConfig(@JsonProperty("tenantId") _tenantId: String,
                 @JsonProperty("clientId") _clientId: String,
                 @JsonProperty("clientSecret") _clientSecret: String,
                 @JsonProperty("clientIdAccManagement") _clientIdAccManagement: String,
                 @JsonProperty("clientSecretAccManagement") _clientSecretAccManagement: String,
                 @JsonProperty("subscriptionManagementApi") _subscriptionManagementApi: ApiConfig,
                 @JsonProperty("dataManagementApi") _dataManagementApi: ApiConfig,
                 @JsonProperty("accountManagementApi") _accountManagementApi: ApiConfig,
                 @JsonProperty("publicationServicesApi") _publicationServicesApi: ApiConfig,
                 @JsonProperty("frontEndUrl") _frontEndUrl: String

                ) {
  val tenantId = _tenantId
  val clientId = _clientId
  val clientSecret = _clientSecret
  val clientIdAccManagement = _clientIdAccManagement
  val clientSecretAccManagement = _clientSecretAccManagement
  val subscriptionManagementApi = _subscriptionManagementApi
  val dataManagementApi = _dataManagementApi
  val accountManagementApi = _accountManagementApi
  val publicationServicesApi = _publicationServicesApi
  val frontEndUrl = _frontEndUrl
  val rampUpUsers: Int = Integer.getInteger("rampUsers", 0).toInt
  val rampUpUsersDuration: Int = Integer.getInteger("rampDuration", 30).toInt
  val onceUsers: Int = Integer.getInteger("atOnceUsers", 1).toInt
  val constUsersPerSec: Int = Integer.getInteger("constantUsersPerSec", 0).toInt
  val constUsersPerSecDuration: Int = Integer.getInteger("constUsersDuration", 0).toInt
}