package controllers;

import models.LoginForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.security.UserService;
import views.html.security.login_form;

public class LoginController extends Controller {

	private static UserService userService = new UserService();

	public static Result login() {
		Form<LoginForm> loginForm = Form.form(LoginForm.class);
		return ok(login_form.render(loginForm));
	}

	public static Result authenticate() {
		Form<LoginForm> loginForm = Form.form(LoginForm.class)
				.bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login_form.render(loginForm));
		}
		if (userService.authenticate(loginForm.get().username,
				loginForm.get().password)) {
			session("username", loginForm.get().username);
			return redirect(routes.Application.index());
		} else {
			flash("error", "Usuario o contraseña incorrectos");
			return badRequest(login_form.render(loginForm));
		}
	}

	public static Result logout() {
		session().clear();
		return redirect(routes.Application.index());
	}
}
