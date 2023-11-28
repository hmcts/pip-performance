package utils.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import config.RootConfig
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

}
