package helpers;

import java.util.HashMap;
import java.util.Map;

import business.Contributor;
import services.ContributorService;

public class ContributorHelper {
	private static ContributorService contributorService = new ContributorService();

	public static Map<String, String> getOptions() {
		Map<String, String> options = new HashMap<String, String>();
		for (Contributor c : contributorService.all()) {
			options.put(c.getCode(), c.getCode());
		}
		return options;
	}
}
