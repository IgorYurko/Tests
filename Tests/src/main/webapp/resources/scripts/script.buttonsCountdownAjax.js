$(document).ready(function() {
	
	$("#submit").button();
	$("#radio").buttonset();
	$("label").addClass("ui-corner-all");
		
	$("#timer").countdown({until: '+0h +0m +30s', format: 'S', significant: 1, compact: true});

	
	setTimeout(function(){
		
		setInterval(function(){
			
			if($("#timer").find("span.countdown-row").text() == "00")
				myAjaxPost({
					url : "test",
					contentType : "application/x-www-form-urlencoded",
					dataName : "answerForm",
					dataVal : "NaN",
					html : $("body")
				}); 
			
		}, 500);
			
	}, 29000);
		
//	countdown.on("change", function(){
//		if($(this) == "0"){
//			
//		}
//	});
	
//	setTimeout(function(){
//		
//		
//	}, 30100);
	
});