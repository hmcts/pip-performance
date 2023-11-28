package config

import com.fasterxml.jackson.annotation.JsonProperty

class ApiConfig(@JsonProperty("scopes") _scope: String,
                @JsonProperty("url") _url: String
               ) {
  val scope = _scope
  val url = _url
}

