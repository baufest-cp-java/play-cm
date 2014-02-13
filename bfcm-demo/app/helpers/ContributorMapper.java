package helpers;

import java.util.ArrayList;
import java.util.List;

import business.Contributor;
import models.ContributorForm;

public class ContributorMapper {

	public static Contributor toModel(ContributorForm form) {
		if (form == null) {
			return null;
		}
		Contributor model = new Contributor();
		model.setId(form.id);
		model.setCode(form.code);
		model.setName(form.name);
		model.setMail(form.mail);
		model.setRol(form.rol);
		model.setUnity(form.unity);
		return model;
	}

	public static ContributorForm toForm(Contributor model) {
		if (model == null) {
			return null;
		}
		ContributorForm form = new ContributorForm();
		form.id = model.getId();
		form.code = model.getCode();
		form.name = model.getName();
		form.mail = model.getMail();
		form.rol = model.getRol();
		form.unity = model.getUnity();
		return form;
	}

	public static List<ContributorForm> toForms(List<Contributor> models) {
		List<ContributorForm> forms = new ArrayList<ContributorForm>();
		for (Contributor model : models) {
			forms.add(toForm(model));
		}
		return forms;
	}
}
