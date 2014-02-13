package helpers;

import java.util.HashMap;
import java.util.Map;

import business.ContributionType;
import services.ContributionTypeService;

public class ContributionTypeHelper {
	private static ContributionTypeService contributionTypeService = new ContributionTypeService();

	public static Map<String, String> getOptions() {
		Map<String, String> options = new HashMap<String, String>();
		for (ContributionType c : contributionTypeService.all()) {
			options.put(c.getCode(), c.getCode());
		}
		return options;
	}
}
