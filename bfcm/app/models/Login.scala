package models

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}

case class Login(username: String, password: String)

object Login {

  val form = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(Login.apply)(Login.unapply)
  )
}
