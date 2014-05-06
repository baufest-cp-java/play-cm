package models

import org.squeryl.KeyedEntity

case class ContributionType(id: Long, code: String, name: String, score: Long) extends KeyedEntity[Long]

object ContributionType {

}