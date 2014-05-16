package controllers

import play.api.mvc.{Action, Controller}
import models.Secured

object Contributions extends Controller with Secured {

  def index()         = TODO
  def get(id:Long)    = TODO
  def create()        = TODO
  def save()          = TODO
  def remove(id:Long) = TODO
}
