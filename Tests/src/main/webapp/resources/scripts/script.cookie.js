$(document).ready(function(){
	

	if (!$.cookie("JQSMILE")) {

		var smile = $("#smile");
		var body = $("body");
		var header = $("header");
		var allNotHeader = $("main").children().not("header");

		showLogo({
			body : body,
			allNotHeader : allNotHeader,
			header : header,
			smile : smile
		});
		header
				.attr(
						"title",
						"Если Вы готовы проверить свои интелектуальные способности, нажимайте и мы начнем!!!")
				.showTooltip();

		header.on("click", function() {
			hideLogo({
				body : body,
				allNotHeader : allNotHeader,
				header : header,
				smile : smile
			});
			$.cookie("JQSMILE", "true");
			$(this).hideTooltip();
		});
	}
});