package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.template;

public class Application extends Controller {

    public static Result index() {
        return ok(template.render("Baufest Contribution Manager", null));
    }

    public static Result login() {
    	return TODO;
    }
    
    public static Result logout() {
    	return TODO;
    }
}
