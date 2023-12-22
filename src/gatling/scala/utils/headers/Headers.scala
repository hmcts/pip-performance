package utils.headers

import scala.util.Random

object Headers {

  // Publication Header
  val headersAPI = Map(
    "Authorization" -> "bearer ${bearerx}",
    "Accept"  -> "application/json",
    "x-provenance"  -> "MANUAL_UPLOAD",
    "x-source-artefact-id"  -> ("artefact-id-perf-" + Random.nextInt(99999999)),
    "x-type"  -> "LIST",
    "x-sensitivity"  -> "PUBLIC",
    "x-language"  -> "ENGLISH",
    "x-display-from"  -> "${startDate}",
    "x-display-to"  -> "${endDate}",
    "x-content-date"  -> "${startDate}",
    "x-court-id" -> "${P&I ID}"
  )

  val headersSub =  Map(

  )

}