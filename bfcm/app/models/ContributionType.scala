package models

import org.squeryl.KeyedEntity
import play.api.data.Form
import play.api.data.Forms._

case class ContributionType(id: Long, code: String, name: String, score: Long) extends KeyedEntity[Long]

object ContributionType extends Entity[ContributionType] {

  val form = Form(
    mapping(
      "id" -> longNumber,
      "code" -> nonEmptyText,
      "name" -> nonEmptyText,
      "score" -> longNumber
    )(ContributionType.apply)(ContributionType.unapply)
  )

  override def insert(entity: ContributionType): Unit = ???

  override def persit(entity: ContributionType): Unit = ???

  override def update(entity: ContributionType): Unit = ???

  override def delete(entity: ContributionType): Unit = ???

  override def delete(id: Long): Unit = ???

  override def list: List[ContributionType] = ???

  override def find(id: Long): Option[ContributionType] = ???
}