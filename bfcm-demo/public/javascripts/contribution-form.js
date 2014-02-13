$(function(){
	addContributorClickAction();
	addFormSubmitAction();
	allRemoveContributorClickAction();
});

appendNewContributor = function(newContributor){
	getContributorsGroup().append(newContributor);
   	
   	newContributor.slideDown(function(){
   		renumberContributorsFields(getContributorsGroup());
   	});
};

getNewContributor = function(){
	var newContributor = $('.dv-contributor-field',getContributorTemplate()).clone();
	newContributor.hide();
	var obj = $('.dv-contributor-template',newContributor);
   	obj.removeClass("dv-contributor-template");
   	obj.addClass("dv-contributor");
   	
   	removeContributorClickAction(newContributor);
   	
   	return newContributor;
};

getContributorTemplate = function(){
	return $('#contributor_template');
};

getContributorsGroup = function(){
	return $('#contributors_group');
};

addFormSubmitAction = function(){
	$('#contribution_form').submit(function() {
	    cleanForm();
	});
};

addContributorClickAction = function(){
	$('#add_contributor').click(function(e) {
        appendNewContributor(getNewContributor());
	});
};

removeContributorClickAction = function(contributor){
	$('.dv-remove-contributor',contributor).click(function(e) {
		contributor.slideUp(function(){
			contributor.remove();
   			renumberContributorsFields(getContributorsGroup());
   		});
	});
};

renumberContributorsFields = function(contributorsGroup) {
	$('.dv-contributor', contributorsGroup).each(function(i) {
	 	$(this).attr('id', $(this).attr('id').replace(/_x*[0-9]*__/g,"_" + i + "__"));
	 	$('label',$(this)).attr("for", $('label',$(this)).attr("for").replace(/_x*[0-9]*__/g,"_" + i + "__"));
	 	$('select',$(this)).attr("id", $('select',$(this)).attr("id").replace(/_x*[0-9]*__/g,"_" + i + "__"));
	 	$('select',$(this)).attr("name", $('select',$(this)).attr("name").replace(/\[x*[0-9]*\]/g,"[" + i + "]"));
	});
};

allRemoveContributorClickAction = function(){
	$('.dv-remove-contributor').click(function(e) {
		var contributor = $(this).parent();
		contributor.slideUp(function(){
			contributor.remove();
   			renumberContributorsFields(getContributorsGroup());
   		});
	});
};

cleanForm = function(){
	$('#contributor_template').remove();
};
