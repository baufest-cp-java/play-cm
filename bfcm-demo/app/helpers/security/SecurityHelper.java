package helpers.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import business.security.Permission;
import business.security.Role;
import business.security.Section;
import services.security.UserService;

public class SecurityHelper {

	private static UserService userService = new UserService();
	
	public static List<Section> getSections(String username){
		List<Section> result = new ArrayList<Section>();
		for(Role role : userService.findByUsername(username).getRoles()){
			for(Permission permission : role.getPermissions()){
				result.add(permission.getSection());
			}
		}
		Collections.sort(result);
		return result;
	}
}
