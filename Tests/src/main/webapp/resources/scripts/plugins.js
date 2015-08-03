(function($){
	
	$.fn.showError = function(){
		return this.slideDown(300, "swing").draggable();
	};
	
	$.fn.showTooltip = function(){
		return this.tooltip({  
						show: {
								effect: "bounce",
								delay: 300}, 
						hide: {
								effect: "shake",
								delay: 300},
						position: {
								my: "left bottom-15%",
								at: "center+5% top"} 
					});
	};
	
	$.fn.hideTooltip = function(){
		return this.tooltip("destroy").removeAttr("title");
	};
	
})(jQuery);