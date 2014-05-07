package controllers

import play.api.mvc.{Action, Controller}
import models.{Role, Contributor, Secured}

object Contributors extends Controller with Secured {

  def index = withAuth { username => implicit request =>
    Ok(views.html.contributors.index(Contributor.list))
  }

  def notFoundMessage(id: Long): String = s"Contributor with id $id not found"

  def get(id: Long) = withAuth { username => implicit request =>
    val contributor = Contributor.find(id)

    if (!contributor.isDefined) {
      flash + "error" -> notFoundMessage(id)
      NotFound(views.html.contributors.index(Contributor.list))
    }

    val findedContributor = contributor.get
    Ok(views.html.contributors.edit(Contributor.form.fill(findedContributor), findedContributor.contributions))
  }

  def create = withAuth { username => implicit request =>
    Ok(views.html.contributors.edit(Contributor.form, List()))
  }

  def save = withAuth { username => implicit request =>
    val contributorForm = Contributor.form.bindFromRequest()

    Role.values.

    contributorForm.fold(
      hasErrors = { form =>
        flash + "error" -> "Error trying to save new contributor"
        BadRequest(views.html.contributors.edit(Contributor.form, List()))
      },

      success = { newContributor =>
        flash + "success" -> "New contributor saved successfully"
        Contributor.persit(newContributor)
        Redirect(routes.Contributors.index)
      }
    )
  }

  def remove(id: Long) = withAuth { username => implicit request =>
    val contributor = Contributor.find(id)

    if (!contributor.isDefined) {
      flash + "error" -> notFoundMessage(id)
      NotFound(views.html.contributors.index(Contributor.list))
    }

    Contributor.delete(id)

    flash + "success" -> "Contributor deleted successfully"
    Redirect(routes.Contributors.index());
  }
}
