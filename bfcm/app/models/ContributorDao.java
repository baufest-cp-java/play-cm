package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContributorDao {
	private static Map<Long, Contributor>	contributors;
	private static Long						nextId;
	
	static {
		nextId = 4L;
		contributors = new HashMap<Long, Contributor>();
		contributors.put(1L, new Contributor(1L, "Diego"));
		contributors.put(2L, new Contributor(2L, "Pablo"));
		contributors.put(3L, new Contributor(3L, "Joel"));
	}
	
	public static Set<Contributor> get() {
		return new HashSet<Contributor>(contributors.values());
	}
	
	public static Contributor get(Long id) {
		return contributors.get(id);
	}

	public static void save(Contributor contributor) {
		if(contributor.getId() == null ) {
			contributor.setId(getNewId());
		}
		
		contributors.put(contributor.getId(), contributor);
	}

	private static Long getNewId() {
		return nextId++;
	}
}