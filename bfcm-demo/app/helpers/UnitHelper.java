package helpers;

import java.util.HashMap;
import java.util.Map;

import enums.Unit;

public class UnitHelper {

	public static Map<String, String> getOptions() {
		Map<String, String> options = new HashMap<String, String>();
		for (Unit c : Unit.values()) {
			options.put(c.name(), c.name());
		}
		return options;
	}
}
