package controllers;

import models.Contributor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.contributors.edit;
import views.html.contributors.index;

public class Contributors extends Controller {
	public static Result index() {
		return ok(index.render(Contributor.get()));
	}
	
	public static Result get(Long id) {
		return TODO;
	}
	
	public static Result create() {
		Form<Contributor> form = Form.form(Contributor.class);
		return ok(edit.render(form));
	}
	
	public static Result save() {
		return TODO;
	}
}
