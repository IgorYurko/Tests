$(document).ready(function() {

	var resTest = parseInt($("#result").find("span").text());
	$("#progressbar").progressbar({
		value : resTest
	});

	$("a").showTooltip();

});