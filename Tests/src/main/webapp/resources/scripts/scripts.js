$(document).ready(function() {

	var err = $(".err");
	var a = $("a");
	var submit = $("#submit");
	var smile = $("#smile");
	
	
	/* Позиционирование смайлика */
	smile.attr("style", $.cookie("JQSMILE")).css({
		zIndex : "10",
		cursor : "move"
	}).draggable();
	
	smile.on("mouseup", function() {
		var res = $(this).attr("style");
		$.cookie("JQSMILE", res);
	});

	/* Проверка на ошибки и их вывод */

	if (err.find("p span").eq(1).text() != "") {
		err.showError();
	}

	/* Подгрузка страниц Ajax по GET запросам */

	a.on("click", function(e) {
		e.preventDefault();
		e.stopPropagation();
		myAjaxGet({
			url : $(this).attr("href"),
			html : $("body")
		});
	});

	/* Подгрузка страниц Ajax по POST запросам */

	submit.on("click", function(e) {
		e.preventDefault();
		e.stopPropagation();
		var getCheck = getChecked($(".check"));

		if (!getCheck.res)
			alert("Вы не выбрали ответ!");
		else
			myAjaxPost({
				url : "test",
				contentType : "application/x-www-form-urlencoded",
				dataName : getCheck.check.attr("name"),
				dataVal : getCheck.check.val(),
				html : $("body")
			});
	});

});
