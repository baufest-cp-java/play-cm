var bfcm = bfcm || {};

bfcm.alerts = bfcm.alerts || (function (){
	var timeout = undefined;
	
	return {
		clear: function () {
			console.log('clear');
			window.clearTimeout(timeout);
		},		
		hide: function () {
			console.log('hide');
			window.setTimeout(function(){$('.alert').hide()}, 1000);
		}
	};
})();

$(function() {
	console.log('function');
	if($('.alert').length > 0) {
		console.log('alerts exists');
		bfcm.alerts.clear();
		bfcm.alerts.hide();
	}
}) 