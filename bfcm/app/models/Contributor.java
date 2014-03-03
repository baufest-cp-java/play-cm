package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Contributor {
	private Long	id;
	private String 	name;

	public Contributor() {}

	public Contributor(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	private static Map<Long, Contributor>		contributors;

	static {
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
		contributor.setId(getNewId());
		contributors.put(contributor.getId(), contributor);
	}

	private static long getNewId() {
		return ((Double) Math.random()).longValue();
	}
	
}
