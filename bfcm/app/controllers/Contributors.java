package controllers;

import models.Contributor;
import models.ContributorDao;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.contributors.edit;
import views.html.contributors.index;

public class Contributors extends Controller {
	
	private static Form<Contributor> form = Form.form(Contributor.class);
	
	public static Result index() {
		return ok(index.render(ContributorDao.get()));
	}
	
	public static Result get(Long id) {
		Contributor contributor = ContributorDao.get(id);
		
		if(contributor == null ) {
			flash("error", "Contributor with id " + id + " not found");
			return notFound(index.render(ContributorDao.get()));
		}
		
		return ok(edit.render(form.fill(contributor)));
	}
	
	public static Result create() {
		return ok(edit.render(form));
	}
	
	public static Result save() {
		Form<Contributor> contributorForm = form.bindFromRequest();
		
		if(contributorForm.hasErrors()) {
			flash("error", "Error trying to save new contributor");
			return badRequest(edit.render(contributorForm));
		}
		
		ContributorDao.save(contributorForm.get());
		flash("success", "New contributor saved successfully");
		return redirect(routes.Contributors.index());
	}
}
