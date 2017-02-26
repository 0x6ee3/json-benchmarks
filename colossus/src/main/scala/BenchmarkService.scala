package colossus.examples


import colossus._
import core._
import service._
import Callback.Implicits._
import protocols.http._
import Http.defaults._
import UrlParsing._
import HttpMethod._

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._

object BenchmarkService {

  implicit object JsonBody extends HttpBodyEncoder[JValue] {
    val jsonHeader            = HttpHeader("Content-Type", "application/json")
    def encode(json: JValue)  = new HttpBody(compact(render(json)).getBytes("UTF-8"), Some(jsonHeader))
  }

  val json : JValue     = ("message" -> "Hello, World!")
  val plaintext         = HttpBody("Hello, World!")
  val serverHeader      = HttpHeader("Server", "Colossus")

  def start(port: Int)(implicit io: IOSystem) {

    val serverConfig = ServerSettings(
      port = port,
      maxConnections = 16384,
      tcpBacklogSize = Some(1024)
    )

    Server.start("benchmark", serverConfig) { new Initializer(_) {

      val dateHeader = new DateHeader
      val headers = HttpHeaders(serverHeader, dateHeader)

      def onConnect = new Service[Http](_){
        def handle = {
          case request @ Get on Root / "hello" => request.ok(plaintext, headers)
          case request @ Get on Root / "json"  => request.ok(json, headers)
        }
      }

    }}
  }
}
