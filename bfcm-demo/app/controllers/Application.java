package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.index.index;
import controllers.actions.AuthenticationAction;

@With(AuthenticationAction.class)
public class Application extends Controller {

	public static Result index() {
		return ok(index.render());
	}

}
