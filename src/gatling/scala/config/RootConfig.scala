package config

import com.fasterxml.jackson.annotation.JsonProperty

class RootConfig(@JsonProperty("tenantId") _tenantId: String,
                 @JsonProperty("clientId") _clientId: String,
                 @JsonProperty("clientSecret") _clientSecret: String,
                 @JsonProperty("subscriptionManagementApi") _subscriptionManagementApi: ApiConfig,
                 @JsonProperty("dataManagementApi") _dataManagementApi: ApiConfig,
                 @JsonProperty("accountManagementApi") _accountManagementApi: ApiConfig,
                 @JsonProperty("channelManagementApi") _channelManagementApi: ApiConfig,
                 @JsonProperty("publicationServicesApi") _publicationServicesApi: ApiConfig
                ) {
  val tenantId = _tenantId
  val clientId = _clientId
  val clientSecret = _clientSecret
  val subscriptionManagementApi = _subscriptionManagementApi
  val dataManagementApi = _dataManagementApi
  val accountManagementApi = _accountManagementApi
  val channelManagementApi = _channelManagementApi
  val publicationServicesApi = _publicationServicesApi
  val rampUpUsers: Int = Integer.getInteger("rampUsers", 0).toInt
  val rampUpUsersDuration: Int = Integer.getInteger("rampDuration", 30).toInt
  val onceUsers: Int = Integer.getInteger("atOnceUsers", 1).toInt
  val constUsersPerSec: Int = Integer.getInteger("constantUsersPerSec", 0).toInt
  val constUsersPerSecDuration: Int = Integer.getInteger("constUsersDuration",0).toInt
}