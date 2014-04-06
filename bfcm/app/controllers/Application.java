package controllers;

import models.Login;
import play.data.Form;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import services.Authentication;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render());
    }

    public static Result login() {
    	return ok(views.html.authentication.login.render(Form.form(Login.class)));
    }
    
    public static Promise<Result> authenticate() {
    	Form<Login> form 	= Form.form(Login.class).bindFromRequest();
    	Login		login 	= new Login();
    	
    	if(!form.hasErrors()) {
    		login = form.get();
    	}

    	Authentication service = new Authentication();
    	
    	return service.authenticate(login);
    }

    public static Result logout() {
    	session().remove("username");
    	flash("success", "Successfuly logged out");
    	return redirect(routes.Application.index());
    }
}
