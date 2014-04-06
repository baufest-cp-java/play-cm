package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.flash;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.status;

import java.util.HashMap;
import java.util.Map;

import models.Contributor;
import models.Role;

import org.junit.Test;

import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;

public class ContributorsTest {

	@Test
	public void testIndexWithoutSession() {
		running(getFakeApp(), new Runnable() {
			@Override
			public void run() {
				Result result = callAction(controllers.routes.ref.Contributors.index());
				assertThat(status(result)).isEqualTo(SEE_OTHER);
				assertThat(contentAsString(result).contains("Index"));
			}
		});
	}

	@Test
	public void testIndexWithSession() {
		running(getFakeApp(), new Runnable() {
			@Override
			public void run() {
				Result result = callAction(controllers.routes.ref.Contributors.index(), getFakeRequest());
				assertThat(status(result)).isEqualTo(OK);
				assertThat(contentAsString(result).contains("Index"));
			}
		});
	}

	@Test
	public void testGetReturnsNotFound() {
		running(getFakeApp(), new Runnable() {
			@Override
			public void run() {
				Result result = callAction(controllers.routes.ref.Contributors.get(1), getFakeRequest());
				assertThat(status(result)).isEqualTo(NOT_FOUND);
			}
		});
	}

	@Test
	public void testGetReturnsSavedContributor() {
		running(getFakeApp(), new Runnable() {
			@Override
			public void run() {
				saveContributor();
				Result result = callAction(controllers.routes.ref.Contributors.get(1), getFakeRequest());
				assertThat(status(result)).isEqualTo(OK);
			}
		});
	}

	@Test
	public void testSaveWithoutDataReturnsBadRequet() {
		running(getFakeApp(), new Runnable() {
			@Override
			public void run() {
				Result result = callAction(controllers.routes.ref.Contributors.save(), getFakeRequest());
				assertThat(status(result)).isEqualTo(BAD_REQUEST);
			}
		});
	}

	@Test
	public void testSaveSuccessfully() {
		running(getFakeApp(), new Runnable() {
			@Override
			public void run() {
				FakeRequest 		req 				= getFakeRequest();
				Map<String, String> contributorAsMap 	= getContributorAsMap();
				req.withFormUrlEncodedBody(contributorAsMap);
				
				Result result = callAction(controllers.routes.ref.Contributors.save(), req);
				
				assertThat(status(result)).isEqualTo(SEE_OTHER);
			}
		});
	}
	
	@Test
	public void testRemovingNonExistentContributor() {
		running(getFakeApp(), new Runnable() {
			@Override
			public void run() {
				Result result = callAction(controllers.routes.ref.Contributors.remove(1L), getFakeRequest());
				assertThat(status(result)).isEqualTo(NOT_FOUND);
				assertThat(flash(result).containsKey("error")).isTrue();
			}
		});
	}

	@Test
	public void testRemoveSuccessfully() {
		running(getFakeApp(), new Runnable() {
			@Override
			public void run() {
				saveContributor();
				Result result = callAction(controllers.routes.ref.Contributors.remove(1L), getFakeRequest());
				assertThat(status(result)).isEqualTo(SEE_OTHER);
				assertThat(flash(result).containsKey("success")).isTrue();
			}
		});
	}

	private FakeRequest getFakeRequest() {
		FakeRequest req = fakeRequest();
		req.withSession("username", "admin");

		return req;
	}

	protected FakeApplication getFakeApp() {
		Map<String,String> settings = new HashMap<String, String>(inMemoryDatabase());
		settings.put("application.secret", "test");

		return fakeApplication(settings);
	}

	protected void saveContributor() {
		Contributor c = getContributor();
		c.save();
	}

	protected Contributor getContributor() {
		Contributor c = new Contributor();

		c.setName("test");
		c.setEmail("test@test.com");
		c.setRole(Role.P1);

		return c;
	}

	protected Map<String, String> getContributorAsMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("name", "test");
		map.put("email", "test@test.com");
		map.put("role", "P1");
		
		return map;
	}
}
