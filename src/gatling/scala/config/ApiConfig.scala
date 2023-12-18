package config

import com.fasterxml.jackson.annotation.JsonProperty

class ApiConfig(@JsonProperty("scopes") _scope: String,
                @JsonProperty("url") _url: String
               ) {
 lazy val scope = _scope
 lazy val url = _url
}

