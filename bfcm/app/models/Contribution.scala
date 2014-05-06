package models

import org.squeryl.KeyedEntity

case class Contribution(id: Long, title: String, contributionType: ContributionType) extends KeyedEntity[Long] {

}

object Contribution {

}