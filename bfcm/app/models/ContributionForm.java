package models;

import java.util.List;
import java.util.Map;

public class ContributionForm {
	public Long			id;
	public String		title;
	public String		contributionType;
	public List<Long>	contributors;	
	
	
	public static ContributionForm get(Contribution contribution) {
		ContributionForm form = new ContributionForm();
		
		form.id = contribution.getId();
		form.title = contribution.getTitle();
		form.contributionType = contribution.getContributionType();
		
		for(Contributor c : contribution.getContributors()) {
			form.contributors.add(c.getId());
		}
		
		return form;
	}
	
	public static Contribution get(ContributionForm form) {
		Contribution contribution = new Contribution();
		
		contribution.setId(form.id);
		contribution.setTitle(form.title);
		contribution.setContributionType(form.contributionType);
		
		for(Long id : form.contributors) {
			contribution.addContributor(Contributor.find().byId(id));
		}
		
		return contribution;
	}

	public static Contribution get(Map<String, String[]> map) {
		Contribution contribution = new Contribution();
		
		contribution.setId(getId(map.get("id")[0]));
		contribution.setTitle(map.get("title")[0]);
		contribution.setContributionType(map.get("contributionType")[0]);
		
		for(String id : map.get("contributors")) {
			contribution.addContributor(Contributor.find().byId(getId(id)));
		}
		
		return contribution;
	}

	private static Long getId(String id) {
		return id == null || "".equals(id) ? null : Long.parseLong(id);
	}
	
	
}
