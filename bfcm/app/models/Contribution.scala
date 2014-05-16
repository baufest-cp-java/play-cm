package models

import org.squeryl.KeyedEntity
import settings.Database
import play.api.data.Form
import play.api.data.Forms._

case class Contribution(id: Long, title: String, contributionType: ContributionType) extends KeyedEntity[Long] {
  lazy val contributors = Database.contributorContributions.rightStateful(this).toList
}

object Contribution {

  val form = Form(
    mapping(
      "id" -> longNumber,
      "title" -> nonEmptyText,
      "contributionType" -> contributionMapping
    )
  )
}