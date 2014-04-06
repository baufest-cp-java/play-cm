package controllers;
import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;

import org.junit.Test;

import play.mvc.Result;

public class ApplicationControllerTest {
	
	@Test
	public void test1() {
		Result result = callAction(controllers.routes.ref.Application.index());
		assertThat(status(result)).isEqualTo(OK);
		assertThat(contentAsString(result).contains("Index"));
	}
	
	@Test
	public void test2() {
		Result result = callAction(controllers.routes.ref.Application.login());
		assertThat(status(result)).isEqualTo(OK);
		assertThat(contentAsString(result).contains("Login"));
	}

}
