package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Message(message: String)
object Message {
  implicit val messageWrites = Json.writes[Message]
}

@Singleton
class HomeController @Inject() extends Controller {

  def index = Action {
    Ok("Hello World!")
  }

  def json = Action {
    val msg = Message("Hello World!")
    Ok(Json.toJson(msg))
  }
}
