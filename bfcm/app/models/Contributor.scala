package models

import models.Role.Role
import org.squeryl.KeyedEntity

case class Contributor(id: Long, name: String, email: String, role: Role) extends KeyedEntity[Long]

object Contributor {

}