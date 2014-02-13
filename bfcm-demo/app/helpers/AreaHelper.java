package helpers;

import java.util.HashMap;
import java.util.Map;

import enums.Area;

public class AreaHelper {

	public static Map<String, String> getOptions() {
		Map<String, String> options = new HashMap<String, String>();
		for (Area c : Area.values()) {
			options.put(c.name(), c.name());
		}
		return options;
	}
}
