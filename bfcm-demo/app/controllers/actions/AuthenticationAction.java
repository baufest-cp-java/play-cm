package controllers.actions;

import helpers.security.SecurityHelper;

import java.util.List;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;
import views.html.errors.bad_request;
import business.security.Section;
import controllers.routes;

public class AuthenticationAction extends Action.Simple{

	@Override
	public Result call(Context ctx) throws Throwable {
		if (ctx.session().containsKey("username")) {
			
			String[] path = ctx.request().path().split("/");
			if(path.length > 0){
				Section currentSection = Section.getSectionFromRelativePath(path[0]);
				if(currentSection != null){
					List<Section> sections = SecurityHelper.getSections(ctx.session().get("username"));
					if(sections.contains(currentSection)){
						return delegate.call(ctx);
					}
				}
			}else if(ctx.request().path().equals("/")){
				return delegate.call(ctx);
			}
			return badRequest(bad_request.render());
		}else{
			return redirect(routes.LoginController.login());
		}
	}

}
