package controllers;

import models.Contributor;
import play.mvc.Controller;
import play.mvc.Result;


public class Contributors extends Controller {
	public static Result index() {
		return ok(views.html.contributors.index.render(Contributor.get()));
	}
	
	public static Result get(Long id) {
		return TODO;
	}
	
	public static Result create() {
		return TODO;
	}
	
	public static Result save() {
		return TODO;
	}
}
