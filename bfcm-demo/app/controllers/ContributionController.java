package controllers;

import helpers.ContributionMapper;
import models.ContributionForm;
import play.Logger;
import play.Logger.ALogger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.ContributionService;
import views.html.contribution.contribution_form;
import views.html.contribution.contribution_list;
import controllers.actions.AuthenticationAction;
import exceptions.ServiceException;

@With(AuthenticationAction.class)
public class ContributionController extends Controller {

	private static ALogger logger = Logger.of(ContributionController.class);
	private static ContributionService contributionService = new ContributionService();

	public static Result list() {
		return ok(contribution_list.render(ContributionMapper
				.toForms(contributionService.all())));
	}

	public static Result create() {
		Form<ContributionForm> contributionForm = Form
				.form(ContributionForm.class);
		return ok(contribution_form.render(null, contributionForm));
	}

	public static Result save() {
		Form<ContributionForm> contributionForm = Form.form(
				ContributionForm.class).bindFromRequest();
		if (contributionForm.hasErrors()) {
			return badRequest(contribution_form.render(null, contributionForm));
		}
		try {
			contributionService.save(ContributionMapper
					.toModel(contributionForm.get()));
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return badRequest(contribution_form.render(null, contributionForm));
		}
		return backToList();
	}

	public static Result edit(Long id) {
		Form<ContributionForm> contributionForm = Form.form(
				ContributionForm.class).fill(
				ContributionMapper.toForm(contributionService.findById(id)));
		return ok(contribution_form.render(id, contributionForm));
	}

	public static Result update(Long id) {
		Form<ContributionForm> contributionForm = Form.form(
				ContributionForm.class).bindFromRequest();
		if (contributionForm.hasErrors()) {
			return badRequest(contribution_form.render(id, contributionForm));
		}
		contributionForm.get().id = id;
		try {
			contributionService.update(ContributionMapper
					.toModel(contributionForm.get()));
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return badRequest(contribution_form.render(id, contributionForm));
		}
		return backToList();
	}

	public static Result delete(Long id) {
		try {
			contributionService.delete(contributionService.findById(id));
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return backToList();
		}
		return backToList();
	}

	private static Result backToList() {
		return redirect(routes.ContributionController.list());
	}
}
