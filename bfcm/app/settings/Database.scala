package settings

import org.squeryl.{Table, Schema}
import models._
import org.squeryl.PrimitiveTypeMode._
import models.ContributorContribution

object Database extends Schema {
  val contributionsTable: Table[Contribution] = table[Contribution]("contribution")
  val contributionTypesTable: Table[ContributionType] = table[ContributionType]("contribution_type")
  val contributorsTable: Table[Contributor] = table[Contributor]("contributor")
  val rolesTable: Table[Role] = table[Role]("role")

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

  on(rolesTable) (r => declare(
    r.id is autoIncremented
  ))
}
