package controllers;

import models.Contribution;
import models.ContributionForm;
import models.Contributor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.contributions.edit;
import views.html.contributions.index;

public class Contributions extends Controller {

	private static Form<ContributionForm> form = Form.form(ContributionForm.class);

	public static Result index() {
		return ok(index.render(Contribution.find().all()));
	}

	public static Result get(Long id) {
		Contribution contribution =   Contribution.find().byId(id);

		if(contribution == null ) {
			flash("error", getNotFoundMessage(id));
			return notFound(index.render(Contribution.find().all()));
		}

		return ok(edit.render(form.fill(ContributionForm.get(contribution)), Contributor.find().all()));
	}

	public static Result create() {
		return ok(edit.render(form, Contributor.find().all()));
	}

	public static Result save() {
		
		System.out.println(request().body().asFormUrlEncoded().get("contributors").length);
		Form<ContributionForm> contributionForm = form.bindFromRequest();

		if(contributionForm.hasErrors()) {
			flash("error", "Error trying to save new contribution");
			return badRequest(edit.render(contributionForm, Contributor.find().all()));
		}

		Contribution contribution = ContributionForm.get(request().body().asFormUrlEncoded());
		if(contribution.getId()	 == null) {
			contribution.save();
		} else {
			contribution.update();
		}

		flash("success", "New contribution saved successfully");
		return redirect(routes.Contributions.index());
	}

	public static Result remove(Long id) {
		Contribution contribution = Contribution.find().byId(id);

		if(contribution == null) {
			flash("error", getNotFoundMessage(id));
			return notFound(index.render(Contribution.find().all()));
		}

		contribution.delete();

		flash("success", "Contribution deleted successfully");
		return redirect(routes.Contributions.index());
	}
		
	private static String getNotFoundMessage(Long id) {
		return "Contribution with id " + id + " not found";
	}

}
