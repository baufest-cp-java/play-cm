package controllers;

import helpers.ContributionTypeMapper;
import models.ContributionTypeForm;
import play.Logger;
import play.Logger.ALogger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.ContributionTypeService;
import views.html.contributiontype.contribution_type_form;
import views.html.contributiontype.contribution_type_list;
import controllers.actions.AuthenticationAction;
import exceptions.ServiceException;

@With(AuthenticationAction.class)
public class ContributionTypeController extends Controller {

	private static ALogger logger = Logger.of(ContributionTypeController.class);
	private static ContributionTypeService contributionTypeService = new ContributionTypeService();

	public static Result list() {
		return ok(contribution_type_list.render(ContributionTypeMapper
				.toForms(contributionTypeService.all())));
	}

	public static Result create() {
		Form<ContributionTypeForm> contributionTypeForm = Form
				.form(ContributionTypeForm.class);
		return ok(contribution_type_form.render(null, contributionTypeForm));
	}

	public static Result save() {
		Form<ContributionTypeForm> contributionTypeForm = Form.form(
				ContributionTypeForm.class).bindFromRequest();
		if (contributionTypeForm.hasErrors()) {
			return badRequest(contribution_type_form.render(null,
					contributionTypeForm));
		}
		try {
			contributionTypeService.save(ContributionTypeMapper
					.toModel(contributionTypeForm.get()));
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return badRequest(contribution_type_form.render(null,
					contributionTypeForm));
		}
		return backToList();
	}

	public static Result edit(Long id) {
		Form<ContributionTypeForm> contributionTypeForm = Form.form(
				ContributionTypeForm.class).fill(
				ContributionTypeMapper.toForm(contributionTypeService
						.findById(id)));
		return ok(contribution_type_form.render(id, contributionTypeForm));
	}

	public static Result update(Long id) {
		Form<ContributionTypeForm> contributionTypeForm = Form.form(
				ContributionTypeForm.class).bindFromRequest();
		if (contributionTypeForm.hasErrors()) {
			return badRequest(contribution_type_form.render(id,
					contributionTypeForm));
		}
		contributionTypeForm.get().id = id;
		try {
			contributionTypeService.update(ContributionTypeMapper
					.toModel(contributionTypeForm.get()));
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return badRequest(contribution_type_form.render(id,
					contributionTypeForm));
		}
		return backToList();
	}

	public static Result delete(Long id) {
		try {
			contributionTypeService
					.delete(contributionTypeService.findById(id));
		} catch (ServiceException se) {
			logger.error(se.getMessage(), se);
			flash("error", se.getMessage());
			return backToList();
		}
		return backToList();
	}

	private static Result backToList() {
		return redirect(routes.ContributionTypeController.list());
	}
}
