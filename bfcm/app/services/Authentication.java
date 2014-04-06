package services;

import static play.mvc.Controller.*;
import models.Login;

import org.joda.time.LocalTime;

import play.Logger;
import play.data.Form;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

import controllers.routes;

public class Authentication {

	public Promise<Result> authenticate(Login login) {
		Promise<Response> 	post = WS.url("http://localhost:9090/users/").post(Json.toJson(login));

		return post.map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response resp) throws Throwable {
				if(resp.getStatus() == play.mvc.Http.Status.OK) {
					Logger.info(LocalTime.now() + " - user logged in successfully");
					return success(resp.asJson());
				} else {
					Logger.error(LocalTime.now() + " - login API returned error");
					return failure(resp.asJson());
				}
			}

		});
	}

	protected static Result success(JsonNode resp) {
		String 		username 	= resp.get("message").get("username").asText();
		session().put("username", String.valueOf(username.hashCode()));
		flash("success", resp.get("status").asText() + " - user " + username + " logged in successfully");

		return redirect(routes.Application.index());
	}

	protected static Result failure(JsonNode resp) {
		flash("error", resp.get("status").asText() + " - " + resp.get("message").asText());
		return badRequest(views.html.authentication.login.render(Form.form(Login.class)));
	}

}
