package controllers;

import java.util.HashSet;

import models.Contribution;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.contributions.edit;
import views.html.contributions.index;

public class Contributions extends Controller {

	private static Form<Contribution> form = Form.form(Contribution.class);

	public static Result index() {
		return ok(index.render(new HashSet<Contribution>(Contribution.find().all())));
	}

	public static Result get(Long id) {
		Contribution contribution =   Contribution.find().byId(id);

		if(contribution == null ) {
			flash("error", getNotFoundMessage(id));
			return notFound(index.render(new HashSet<Contribution>(Contribution.find().all())));
		}

		return ok(edit.render(form.fill(contribution)));
	}

	public static Result create() {
		return ok(edit.render(form));
	}

	public static Result save() {
		Form<Contribution> contributionForm = form.bindFromRequest();

		if(contributionForm.hasErrors()) {
			flash("error", "Error trying to save new contribution");
			return badRequest(edit.render(contributionForm));
		}

		Contribution contribution = contributionForm.get();
		if(contribution.getId() == null) {
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
			return notFound(index.render(new HashSet<Contribution>(Contribution.find().all())));
		}

		contribution.delete();

		flash("success", "Contribution deleted successfully");
		return redirect(routes.Contributions.index());
	}

	private static String getNotFoundMessage(Long id) {
		return "Contribution with id " + id + " not found";
	}

}
