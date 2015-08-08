
	function showLogo(ObjOptions){
		
		ObjOptions.body.addClass("overflowBody");
		ObjOptions.allNotHeader.hide();
	
		setTimeout(function(){
			ObjOptions.header.addClass("showLogo");
			ObjOptions.smile.addClass("scaleSmile");
		}, 300);
	}

	function hideLogo(ObjOptions){
		
		ObjOptions.header.removeClass().addClass("hideLogo");
		ObjOptions.smile.removeClass().addClass("defaultSmile");
		
		setTimeout(function(){
			ObjOptions.allNotHeader.fadeIn(300);
			}, 900);
		
		ObjOptions.body.removeClass();
		
	}

	function myAjaxGet(ObjOptions){

			$.ajax({
				type: "GET",
				url: ObjOptions.url,
				contentType: "text/html",
				success: function(data){
					ObjOptions.html.hide().html(data).fadeIn(300);
				},
				error: function(){
					allert("Не сегодня!!!");
				}
			});
	};
		
	function getChecked(jQueryOption){
		
		var res = false;
		var check;
		
		jQueryOption.each(function(indx) {

			if ($(this).prop("checked") == true) {
				res = true;
				check = $(this);
			}
		});
		
		return new Object({
							res: res,
							check: check
						});
	};

	function myAjaxPost(ObjOptions){
		
			$.ajax({
	  			type: "POST",
	  			url: ObjOptions.url,
	  			contentType: ObjOptions.contentType,
	  			data: ObjOptions.dataName + "=" + ObjOptions.dataVal
	  				  ,
	  			success: function(data){
	  					ObjOptions.html.hide().html(data).fadeIn(300);
	  			},
	  			error: function(){
	  				setTimeout(function(){
	  					alert("ERROR! Попробуйте познее.");			
	  				},400);
	  			}
	  		});
		
	};
	
	
