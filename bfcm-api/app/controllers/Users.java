package controllers;

import models.User;
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
		
		String 	 username 	= data.findPath("username").asText();
		String 	 password 	= data.findPath("password").asText();

		User user = User.find(username, password);

		if(user == null) {
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
