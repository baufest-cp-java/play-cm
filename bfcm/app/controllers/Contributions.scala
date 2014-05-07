package controllers

import play.api.mvc.{Action, Controller}
import models.Secured

object Contributions extends Controller with Secured {

  def index()         = withAuth { username => implicit request => Ok() }
  def get(id:Long)    = withAuth { username => implicit request => Ok() }
  def create()        = withAuth { username => implicit request => Ok() }
  def save()          = withAuth { username => implicit request => Ok() }
  def remove(id:Long) = withAuth { username => implicit request => Ok() }
}
