package controllers

import play.api.{Application, GlobalSettings}
import org.squeryl.{Session, SessionFactory}
import play.api.db.DB
import org.squeryl.adapters.H2Adapter

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    SessionFactory.concreteFactory = Some(() =>
      Session.create(DB.getConnection()(app), new H2Adapter) )
  }
}