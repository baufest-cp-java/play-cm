package models;

import java.util.Arrays;
import java.util.List;

public enum Role {
	P1, P2, P3, P4, P5, P6, SDL, RUP;
	
	public static List<Role> getRoleList() {
		return Arrays.asList(Role.values());
	}
}
