package controllers;

import models.Login;
import play.data.Form;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;
import play.libs.WS.Response;
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
    		return badRequest(views.html.authentication.login.render(Form.form(Login.class)));
    	}
    	
    	Promise<Response> post = WS.url("http://localhost:9090/users/").post(Json.toJson(form.get()));
    	
    	Promise<Result> result = post.map(new Function<WS.Response, Result>() {

			@Override
			public Result apply(Response resp) throws Throwable {
				if(resp.getStatus() == OK) {
					String username = resp.asJson().findPath("username").asText();
					session().put("username", String.valueOf(username.hashCode()));
					
					return redirect(routes.Application.index());
				} else {
					flash("error", resp.asJson().findPath("result").asText());
		    		return badRequest(views.html.authentication.login.render(Form.form(Login.class)));
				}
			}
    		
		});
    	
    	return result.get(OK);
    }
    
    public static Result logout() {
    	session().remove("username");
    	flash("success", "Successfuly logged out");
    	return redirect(routes.Application.index());
    }
}
