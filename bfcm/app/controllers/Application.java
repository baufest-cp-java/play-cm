package controllers;

import models.Login;

import org.joda.time.LocalTime;

import play.Logger;
import play.data.Form;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

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
    	
    	Promise<Response> 	post = WS.url("http://localhost:9090/users/").post(Json.toJson(login));
    	
    	Promise<Result> result = post.map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(Response resp) throws Throwable {
				JsonNode json = resp.asJson();
				
				if(resp.getStatus() == OK) {
					String username = json.get("message").get("username").asText();
					session().put("username", String.valueOf(username.hashCode()));

					Logger.info(LocalTime.now() + " - user " + username + " logged in successfully");
					flash("success", json.get("status").asText() + " - user " + username + " logged in successfully");
					
					return redirect(routes.Application.index());
				} else {
					Logger.error(LocalTime.now() + " - login API returned error");
					flash("error", json.get("status").asText() + " - " + json.get("message").asText());
					
		    		return badRequest(views.html.authentication.login.render(Form.form(Login.class)));
				}
			}
    		
		});
    	
    	return result;
    }
        
    public static Result logout() {
    	session().remove("username");
    	flash("success", "Successfuly logged out");
    	return redirect(routes.Application.index());
    }
}
