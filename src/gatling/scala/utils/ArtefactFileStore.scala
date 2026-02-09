package utils

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

object ArtefactFileStore {
  val path = Paths.get("src/gatling/resources/feederOutput/artefactId.csv")

  def write(id: String): Unit =
    Files.write(path, id.getBytes(StandardCharsets.UTF_8))

  def read(): String =
    new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
}
