package controllers;

import models.Login;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result login() {
    	return ok(views.html.authentication.login.render(Form.form(Login.class)));
    }
    
    public static Result authenticate() {
    	if(ctx().session().containsKey("username")) {
    		return redirect(routes.Application.index());
    	}
    	
    	Form<Login> form = Form.form(Login.class).bindFromRequest();

    	if(form.hasErrors()) {
    		flash("error", "Error logging in");
    		return badRequest(views.html.authentication.login.render(form));
    	}
    	
    	session().put("username", String.valueOf(form.get().hashCode()));
    	return redirect(routes.Application.index());
    }
    
    public static Result logout() {
    	session().remove("username");
    	flash("success", "Successfuly logged out");
    	return redirect(routes.Application.index());
    }
}
