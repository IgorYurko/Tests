$(document).ready(function() {
	
	$("#submit").button();
	$("#radio").buttonset();
	$("label").addClass("ui-corner-all");
		
	$("div.timerCss").attr({class: "timerJquery"}).countdown({until: '+30s', format: 'S', significant: 1, compact: true}).clearQueue();

	setTimeout(function(){
		
		setInterval(function(){
			
			var timer = $("div.timerJquery");
			
			if(timer.find("span.countdown-row").text() === "00"){
				timer.countdown("pause");
				timer.find("span.countdown-row").text("");
				myAjaxPost({
					url : "test-js",
					contentType : "application/x-www-form-urlencoded",
					dataName : "answerForm",
					dataVal : "NaN",
					html : $("body")
				});
			}
			
		}, 500);
		
	}, 29000);
		
});