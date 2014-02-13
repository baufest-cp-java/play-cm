package helpers;

import java.util.HashMap;
import java.util.Map;

import enums.Rol;

public class RolHelper {

	public static Map<String, String> getOptions() {
		Map<String, String> options = new HashMap<String, String>();
		for (Rol c : Rol.values()) {
			options.put(c.name(), c.name());
		}
		return options;
	}
}
