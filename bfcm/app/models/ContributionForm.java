package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import play.data.validation.Constraints.Required;

public class ContributionForm {
	public Long			id;
	@Required
	public String		title;
	@Required
	public Long			contributionType;
	@Required
	public List<Long>	contributors;
	public String		description;
	
	public ContributionForm() {
		this.contributors = new ArrayList<Long>();
	}

	public static ContributionForm get(Contribution contribution) {
		ContributionForm form = new ContributionForm();
		
		form.id = contribution.getId();
		form.title = contribution.getTitle();
		form.contributionType = contribution.getContributionType().getId();
		
		for(Contributor c : contribution.getContributors()) {
			form.contributors.add(c.getId());
		}
		
		return form;
	}
	
	public static Contribution get(ContributionForm form) {
		Contribution contribution = new Contribution();
		
		contribution.setId(form.id);
		contribution.setTitle(form.title);
		contribution.setContributionType(ContributionType.find().byId(form.contributionType));
		
		for(Long id : form.contributors) {
			contribution.addContributor(Contributor.find().byId(id));
		}
		
		return contribution;
	}

	public static Contribution get(Map<String, String[]> map) {
		Contribution contribution = new Contribution();
		
		contribution.setId(getId(map.get("id")[0]));
		contribution.setTitle(map.get("title")[0]);
		contribution.setContributionType(ContributionType.find().byId(getId(map.get("contributionType")[0])));
		
		if(map.get("contributors") != null) {
			for(String id : map.get("contributors")) {
				contribution.addContributor(Contributor.find().byId(getId(id)));
			}
		}
		
		return contribution;
	}

	private static Long getId(String id) {
		return id == null || "".equals(id) ? null : Long.parseLong(id);
	}
	
	
}
