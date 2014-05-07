package models

import org.squeryl.KeyedEntity
import org.squeryl.dsl.CompositeKey2
import settings.Database
import play.api.data.Form
import play.api.data.Forms._
import models.Role.Role

case class Contributor(id: Long, name: String, email: String, role: Role) extends KeyedEntity[Long] {
  lazy val contributions = Database.contributorContributions.leftStateful(this).toList
}

case class ContributorContribution(contributorId: Long, contributionId: Long) extends KeyedEntity[CompositeKey2[Long, Long]] {
  override def id = CompositeKey2(contributorId, contributionId)
}

object Contributor extends Entity[Contributor] {

  import org.squeryl.PrimitiveTypeMode._
  import Database.contributorsTable

  val roleMapping = text.transform(
    (stringRole: String) =>
      Role.withName(stringRole),
    (role: Role) =>
      role.toString
  )

  val form = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "email" -> nonEmptyText,
      "role" -> roleMapping
    )(Contributor.apply)(Contributor.unapply)
  )

  def list = {
    from(contributorsTable) {
      contributor => select(contributor)
    }.toList
  }

  override def find(id: Long): Option[Contributor] = {
    from(contributorsTable) { contributor =>
      where(contributor.id e id).select(contributor)
    }.headOption
  }

  override def insert(entity: Contributor): Unit = {
    contributorsTable.insert(entity)
  }

  override def persit(entity: Contributor): Unit = {
    entity.id match {
      case 0 => insert(entity)
      case _ => update(entity)
    }
  }

  override def update(entity: Contributor): Unit = {
    contributorsTable.update(entity)
  }

  override def delete(entity: Contributor): Unit = {
    contributorsTable.delete(entity)
  }

  override def delete(id: Long): Unit = {
    contributorsTable.deleteWhere( contributor =>
      contributor.id e id
    )
  }
}