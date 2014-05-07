package settings

import org.squeryl.{Table, Schema}
import models.{ContributorContribution, Contributor, ContributionType, Contribution}
import org.squeryl.PrimitiveTypeMode._

object Database extends Schema {
  val contributionsTable: Table[Contribution] = table[Contribution]("contribution")
  val contributionTypesTable: Table[ContributionType] = table[ContributionType]("contribution_type")
  val contributorsTable: Table[Contributor] = table[Contributor]("contributor")

  val contributorContributions = manyToManyRelation(contributorsTable, contributionsTable).
    via[ContributorContribution]((contributor, contribution, contributorContribution) => (contributorContribution.contributionId === contribution.id, contributorContribution.contributorId === contribution.id))

  on(contributionsTable) (c => declare(
  c.id is autoIncremented
  ))

  on(contributionTypesTable) (c => declare(
  c.id is autoIncremented
  ))

  on(contributorsTable) (c => declare(
  c.id is autoIncremented
  ))

}
