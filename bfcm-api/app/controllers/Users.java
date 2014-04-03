package controllers;

import org.joda.time.LocalTime;

import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Users extends Controller {
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result get() {
		JsonNode data 		= request().body().asJson();
		
		Logger.info(LocalTime.now() + "- Log ing attempt - data: " + data);
		
		String 	 username 	= data.findPath("username").asText();
		String 	 password 	= data.findPath("password").asText();

		User user = User.find(username, password);

		if(user == null) {
			Logger.error(LocalTime.now() + "- Error trying to log in- username: " + username + " password: " + password);
			return forbidden(getForbidden());
		}

		return ok(getResult(user));
	}

	private static ObjectNode getForbidden() {
		return Json.newObject()
				.put("status", "Unauthorized")
				.put("result", "Username or Password is incorrect");
	}

	private static JsonNode getResult(User user) {
		ObjectNode result = Json.newObject();
		
		result.put("username", user.name);
		result.put("email", user.email);
		result.put("role", user.role.name());
		
		return result;
	}

}
