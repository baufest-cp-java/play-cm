import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;
import play.GlobalSettings;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import views.html.errors.bad_request;
import views.html.errors.internal_server_error;
import views.html.errors.not_found;

public class Global extends GlobalSettings{

	@Override
	public Result onError(RequestHeader arg0, Throwable arg1) {
		return internalServerError(internal_server_error.render());
	}
	
	@Override
	public Result onHandlerNotFound(RequestHeader arg0) {
		return notFound(not_found.render());
	}

	@Override
	public Result onBadRequest(RequestHeader arg0, String arg1) {
		return badRequest(bad_request.render());
	}
	
}
