package controllers

import play.api._
import play.api.mvc._
import models.Login
import services.AuthenticationService
import scala.concurrent.Future

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def login = Action {
    Ok(views.html.authentication.login(Login.form))
  }

  def logout = Action {
    session - "username"
    flash + "success" -> "Successfully logged out"
    Redirect(routes.Application.index())
  }

  def authenticate = Action { implicit request =>
    val loginForm = Login.form

    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.authentication.login(formWithErrors)),
      user => Redirect(routes.Application.index).withSession(Security.username -> user.username)
    )
  }

}