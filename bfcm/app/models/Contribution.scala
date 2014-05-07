package models

import org.squeryl.KeyedEntity
import settings.Database

case class Contribution(id: Long, title: String, contributionType: ContributionType) extends KeyedEntity[Long] {
  lazy val contributors = Database.contributorContributions.right(this)
}

object Contribution {

}