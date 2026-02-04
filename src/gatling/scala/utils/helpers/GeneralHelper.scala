package utils.helpers

import io.gatling.core.session.Session

import java.io.{File, PrintWriter}

object GeneralHelper {

  def saveArtefactId(artefactIdFileName: String, session: Session): Session = {
    val artefactId = session("artefactId").as[String]
    val writer = new PrintWriter(new File(artefactIdFileName))
    writer.println("artefactId")
    writer.println(artefactId)
    writer.close()
    session
  }

  def readFirstArtefactId(file: String): String = {
    val source = scala.io.Source.fromFile(file)
    try {
      source.getLines().drop(1).nextOption().getOrElse("").split(",").headOption.getOrElse("")
    } finally {
      source.close()
    }
  }

}