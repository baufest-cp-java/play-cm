package controllers;

import java.util.HashSet;

import models.ContributionType;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.contributionTypes.*;

public class ContributionTypes extends Controller {
	
	private static Form<ContributionType> form = Form.form(ContributionType.class);
	
	public static Result index() {
		return ok(index.render(new HashSet<ContributionType>(ContributionType.find().all())));
	}
	
	public static Result get(Long id) {
		ContributionType contributionType = ContributionType.find().byId(id);
		
		if(contributionType == null ) {
			flash("error", getNotFoundMessage(id));
			return notFound(index.render(new HashSet<ContributionType>(ContributionType.find().all())));
		}
		
		return ok(edit.render(form.fill(contributionType)));
	}

	public static Result create() {
		return ok(edit.render(form));
	}
	
	public static Result save() {
		Form<ContributionType> contributionTypeForm = form.bindFromRequest();
		
		if(contributionTypeForm.hasErrors()) {
			flash("error", "Error trying to save new contribution type");
			return badRequest(edit.render(contributionTypeForm));
		}
		
		ContributionType contributionType = contributionTypeForm.get();
		if(contributionType.getId() == null) {
			contributionType.save();
		} else {
			contributionType.update();
		}
		
		flash("success", "New contribution type saved successfully");
		return redirect(routes.ContributionTypes.index());
	}
	
	public static Result remove(Long id) {
		ContributionType contributionType = ContributionType.find().byId(id);
		
		if(contributionType == null) {
			flash("error", getNotFoundMessage(id));
			return notFound(index.render(new HashSet<ContributionType>(ContributionType.find().all())));
		}
		
		contributionType.delete();
		
		flash("success", "Contribution type deleted successfully");
		return redirect(routes.ContributionTypes.index());
	}
	
	private static String getNotFoundMessage(Long id) {
		return "Contribution type with id " + id + " not found";
	}
	
}
