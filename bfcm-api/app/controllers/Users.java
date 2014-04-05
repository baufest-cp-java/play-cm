package controllers;

import models.User;

import org.joda.time.LocalTime;

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
		JsonNode json = request().body().asJson();
		Logger.info(LocalTime.now() + "- Login attempt - data: " + json);
		
		String 	 username 	= json.findPath("username").asText();
		String 	 password 	= json.findPath("password").asText();

		User user = User.find(username, password);

		if(user == null) {
			Logger.error(LocalTime.now() + "- Error trying to login - username: " + username + " password: " + password);
			return forbidden(getResponseMessage("Unauthorized", "Username or Password is incorrect"));
		}

		return ok(getResponseMessage("OK", user));
	}

	private static JsonNode getResponseMessage(String status, User user) {
		ObjectNode result = Json.newObject();
		
		result.put("status", status);
		result.put("message", convertUser(user));
		
		return result;
	}

	private static JsonNode convertUser(User user) {
		ObjectNode result = Json.newObject();
		
		result.put("username", user.name);
		result.put("email", user.email);
		result.put("role", user.role.name());
		
		return result;
	}

	private static JsonNode getResponseMessage(String status, String result) {
		return Json.newObject().put("status", status).put("message", result);
	}
}
