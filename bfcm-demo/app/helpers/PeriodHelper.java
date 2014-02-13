package helpers;

import java.util.HashMap;
import java.util.Map;

import enums.Period;

public class PeriodHelper {

	public static Map<String, String> getOptions() {
		Map<String, String> options = new HashMap<String, String>();
		for (Period c : Period.values()) {
			options.put(c.name(), c.name());
		}
		return options;
	}
}
