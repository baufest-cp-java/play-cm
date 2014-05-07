package services

import models.Login
import play.api.libs.ws.{Response, WS}
import play.libs.Json
import scala.concurrent.Future
import play.api.libs.json.JsValue
import play.api.mvc.Result
import play.mvc.Controller.{session, flash}
import play.api.mvc.Results.{Redirect, BadRequest}
import controllers.routes

object Authentication {

  def success(value: JsValue): Result = {
    val username = (value \ "message" \ "username").as[String]
    session + "username" -> username.hashCode.toString
    flash + "success" -> s"${(value \ "status").toString()} - user $username logged in successfully"

    Redirect(routes.Application.index())
  }

  def failure(value: JsValue): Result = {
    flash + "error" -> s"${(value \ "status").toString()} - ${(value \ "message").toString()}"
    BadRequest(views.html.authentication.login(Login.form))
  }

  def authenticate(login: Login) = {
    val post: Future[Response] = WS.url("http://localhost:9090/users/").post(Json.toJson(login))

    post.map( response =>
      response.status match {
        case play.mvc.Http.Status.OK => success(response.json)
        case _ => failure(response.json)
      }
    )
  }
}
