package controllers;

import helpers.ContributorMapper;

import java.util.List;

import controllers.actions.AuthenticationAction;
import models.ContributorForm;
import play.Logger;
import play.Logger.ALogger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.ContributorService;
import views.html.contributor.contributor_form;
import views.html.contributor.contributor_list;
import business.Contributor;
import exceptions.ServiceException;

@With(AuthenticationAction.class)
public class ContributorController extends Controller {

	private static ALogger logger = Logger.of(ContributorController.class);
	private static ContributorService contributorService = new ContributorService();

	public static Result list() {
		List<Contributor> models = contributorService.all();
		List<ContributorForm> forms = ContributorMapper.toForms(models);
		return ok(contributor_list.render(forms));
	}

	public static Result create() {
		Form<ContributorForm> contributorForm = Form
				.form(ContributorForm.class);
		return ok(contributor_form.render(null, contributorForm));
	}

	public static Result edit(Long id) {
		Contributor model = contributorService.findById(id);
		ContributorForm form = ContributorMapper.toForm(model);
		Form<ContributorForm> contributorForm = Form
				.form(ContributorForm.class).fill(form);
		return ok(contributor_form.render(id, contributorForm));
	}

	public static Result save() {
		Form<ContributorForm> contributorForm = Form
				.form(ContributorForm.class).bindFromRequest();
		if (contributorForm.hasErrors()) {
			return badRequest(contributor_form.render(null, contributorForm));
		}
		try {
			ContributorForm form = contributorForm.get();
			Contributor model = ContributorMapper.toModel(form);
			contributorService.save(model);
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return badRequest(contributor_form.render(null, contributorForm));
		}
		return backToList();
	}

	public static Result update(Long id) {
		Form<ContributorForm> contributorForm = Form
				.form(ContributorForm.class).bindFromRequest();
		if (contributorForm.hasErrors()) {
			return badRequest(contributor_form.render(id, contributorForm));
		}
		contributorForm.get().id = id;
		try {
			contributorService.update(ContributorMapper.toModel(contributorForm
					.get()));
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return badRequest(contributor_form.render(id, contributorForm));
		}
		return backToList();
	}

	public static Result delete(Long id) {
		Contributor contributor = contributorService.findById(id);
		try {
			contributorService.delete(contributor);
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return backToList();
		}
		return backToList();
	}

	private static Result backToList() {
		return redirect(routes.ContributorController.list());
	}
}
