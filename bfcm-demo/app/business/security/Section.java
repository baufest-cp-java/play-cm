package business.security;

public enum Section {
	INDEX("Home", "", 1),
	CONTRIBUTOR("Contribuyentes", "contributors", 2), 
	CONTRIBUTION_TYPE("Tipos de contribuciones", "contribution-types", 3), 
	CONTRIBUTION("Contribuciones", "contributions", 4);
	
	private String description;
	private String relativePath;
	private Integer position;

	Section(String description, String relativePath, Integer position) {
		this.description = description;
		this.relativePath = relativePath;
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public String getRelativePath() {
		return relativePath;
	}
	
	public Integer getPosition() {
		return position;
	}

	public static Section getSectionFromRelativePath(String relativePath){
		for(Section section : Section.values()){
			if(section.getRelativePath().equals(relativePath)){
				return section;
			}
		}
		return null;
	}
	
}
