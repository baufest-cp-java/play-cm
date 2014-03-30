bfcm = {};

bfcm.alerts = bfcm.alerts || (function (){
	var timeout = undefined;
	
	return {
		clear: function () {
			console.log('clear');
			window.clearTimeout(timeout);
		},		
		hide: function () {
			console.log('hide');
			window.setTimeout(function(){$('.alert').hide()}, 1500);
		}
	};
})();

bfcm.contributions = bfcm.contributions || (function (){
	return {
		create: function () {
			$('.modal-title').html('Contribution');
			$('.modal-body').load('/admin/contributions/create/ #contribution-form', function () {
				$('#contribution-form .actions').hide();				
				$('.modal-footer #save').on('click', function() {
					$('#contribution-form').submit();
				})
			});
			
			$('.modal').modal('show');
		}
	}
})(); 


$(function() {
	console.log('function');
	if($('.alert').length > 0) {
		console.log('alerts exists');
		bfcm.alerts.clear();
		bfcm.alerts.hide();
	}
	
	$('#new-contribution').on('click', function(e) {
		e.preventDefault();
		bfcm.contributions.create();
	});
}) 