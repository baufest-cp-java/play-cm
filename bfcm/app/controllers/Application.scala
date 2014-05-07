package controllers

import play.api._
import play.api.mvc._
import models.Login

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
    val loginForm = Login.form.bindFromRequest()

    loginForm.fold(
      hasErrors = { form =>
        Redirect(views.html.authentication.login(Login.form))
      },

      success = { login =>

      }
    )
  }

}