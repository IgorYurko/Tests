$(document).ready(function() {
	
	$("#submit").button();
	$("#radio").buttonset();
	$("label").addClass("ui-corner-all");	
	
	$("#timer").countdown({until: '+0h +0m +30s', format: 'S', significant: 1, compact: true});
	
	setTimeout(function(){
		myAjaxPost({
			url : "test",
			contentType : "application/x-www-form-urlencoded",
			dataName : "answerForm",
			dataVal : "NaN",
			html : $("body")
		});
	}, 30100);
	
});