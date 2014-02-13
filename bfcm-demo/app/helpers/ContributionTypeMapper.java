package helpers;

import java.util.ArrayList;
import java.util.List;

import business.ContributionType;
import models.ContributionTypeForm;

public class ContributionTypeMapper {

	public static ContributionType toModel(ContributionTypeForm form) {
		if (form == null) {
			return null;
		}
		ContributionType model = new ContributionType();
		model.setId(form.id);
		model.setCode(form.code);
		model.setName(form.name);
		model.setShortName(form.shortName);
		model.setArea(form.area);
		model.setScore(form.score);
		model.setUnit(form.unit);
		model.setPeriod(form.period);
		model.setDescription(form.description);
		model.setSequence(form.sequence);
		model.setMotivation(form.motivation);
		return model;
	}

	public static ContributionTypeForm toForm(ContributionType model) {
		if (model == null) {
			return null;
		}
		ContributionTypeForm form = new ContributionTypeForm();
		form.id = model.getId();
		form.code = model.getCode();
		form.name = model.getName();
		form.shortName = model.getShortName();
		form.area = model.getArea();
		form.score = model.getScore();
		form.unit = model.getUnit();
		form.period = model.getPeriod();
		form.description = model.getDescription();
		form.sequence = model.getSequence();
		form.motivation = model.getMotivation();
		return form;
	}

	public static List<ContributionTypeForm> toForms(List<ContributionType> models) {
		List<ContributionTypeForm> forms = new ArrayList<ContributionTypeForm>();
		for (ContributionType model : models) {
			forms.add(toForm(model));
		}
		return forms;
	}
}
