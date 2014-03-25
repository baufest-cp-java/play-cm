package controllers;

import java.util.HashSet;

import models.Contribution;
import models.Contributor;
import models.Role;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.contributors.*;

public class Contributors extends Controller {
	
	private static Form<Contributor> form = Form.form(Contributor.class);
	
	public static Result index() {
		return ok(index.render(new HashSet<Contributor>(Contributor.find().all())));
	}
	
	public static Result get(Long id) {
		Contributor contributor =   Contributor.find().byId(id);
		
		if(contributor == null ) {
			flash("error", getNotFoundMessage(id));
			return notFound(index.render(new HashSet<Contributor>(Contributor.find().all())));
		}
		
		return ok(edit.render(form.fill(contributor), contributor.getContributions(), Role.getRoleList()));
	}

	public static Result create() {
		return ok(edit.render(form, new HashSet<Contribution>(), Role.getRoleList()));
	}
	
	public static Result save() {
		Form<Contributor> contributorForm = form.bindFromRequest();
		
		if(contributorForm.hasErrors()) {
			flash("error", "Error trying to save new contributor");
			return badRequest(edit.render(contributorForm, new HashSet<Contribution>(), Role.getRoleList()));
		}
		
		Contributor contributor = contributorForm.get();
		if(contributor.getId() == null) {
			contributor.save();
		} else {
			contributor.update();
		}
		
		flash("success", "New contributor saved successfully");
		return redirect(routes.Contributors.index());
	}
	
	public static Result remove(Long id) {
		Contributor contributor = Contributor.find().byId(id);
		
		if(contributor == null) {
			flash("error", getNotFoundMessage(id));
			return notFound(index.render(new HashSet<Contributor>(Contributor.find().all())));
		}
		
		contributor.delete();
		
		flash("success", "Contributor deleted successfully");
		return redirect(routes.Contributors.index());
	}
	
	private static String getNotFoundMessage(Long id) {
		return "Contributor with id " + id + " not found";
	}
	
}
