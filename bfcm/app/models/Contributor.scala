package models

import models.Role.Role
import org.squeryl.KeyedEntity
import org.squeryl.dsl.CompositeKey2
import settings.Database

case class Contributor(id: Long, name: String, email: String, role: Role) extends KeyedEntity[Long] {
  lazy val contributions = Database.contributorContributions.left(this)
}

case class ContributorContribution(contributorId: Long, contributionId: Long) extends KeyedEntity[CompositeKey2[Long, Long]] {
  override def id = CompositeKey2(contributorId, contributionId)
}

object Contributor {

}