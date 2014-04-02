package models;

import controllers.routes;
import play.mvc.Http.Context;
import play.mvc.Result;

public class Authenticator extends play.mvc.Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("username");
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		ctx.flash().put("error", "This action needs authentication");
		return redirect(routes.Application.login());
	}
}