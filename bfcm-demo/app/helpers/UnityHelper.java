package helpers;

import java.util.HashMap;
import java.util.Map;

import enums.Unity;

public class UnityHelper {

	public static Map<String, String> getOptions() {
		Map<String, String> options = new HashMap<String, String>();
		for (Unity c : Unity.values()) {
			options.put(c.name(), c.name());
		}
		return options;
	}
}
