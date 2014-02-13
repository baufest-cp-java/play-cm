package helpers;

import java.util.ArrayList;
import java.util.List;

import models.ContributionForm;
import models.ContributorCode;
import services.ContributionTypeService;
import services.ContributorService;
import business.Contribution;
import business.Contributor;

public class ContributionMapper {

	private static ContributionTypeService contributionTypeService = new ContributionTypeService();
	private static ContributorService contributorService = new ContributorService();

	public static Contribution toModel(ContributionForm form) {
		if (form == null) {
			return null;
		}
		Contribution model = new Contribution();
		model.setId(form.id);
		model.setContributionType(contributionTypeService.findByCode(form.contributionTypeCode));
		
		List<Contributor> contributorsModels = new ArrayList<Contributor>();
		for(ContributorCode contributorCode : form.contributors){
			Contributor cont = contributorService.findByCode(contributorCode.code);
			if(!contributorsModels.contains(cont)){
				contributorsModels.add(cont);
			}
		}
		model.setContributors(contributorsModels);
		
		model.setDescription(form.description);
		model.setEvidence(form.evidence);
		return model;
	}

	public static ContributionForm toForm(Contribution model) {
		if (model == null) {
			return null;
		}
		ContributionForm form = new ContributionForm();
		form.id = model.getId();
		form.contributionTypeCode = model.getContributionType().getCode();
		
		List<ContributorCode> contributorsCode = new ArrayList<ContributorCode>();
		for(Contributor contributor : model.getContributors()){
			ContributorCode contributorCode = new ContributorCode();
			contributorCode.code = contributor.getCode();
			contributorsCode.add(contributorCode);
		}
		form.contributors = contributorsCode;
		
		form.description = model.getDescription();
		form.evidence = model.getEvidence();
		return form;
	}

	public static List<ContributionForm> toForms(List<Contribution> models) {
		List<ContributionForm> forms = new ArrayList<ContributionForm>();
		for (Contribution model : models) {
			forms.add(toForm(model));
		}
		return forms;
	}
}
