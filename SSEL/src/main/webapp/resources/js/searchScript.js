var isUsed = false; 
function searchSubjects() {
	var searchText = $("#searchInput").val();
	var reg = new RegExp(searchText, "i");
	var isEmpty = false;
	isUsed = true;
	var a = $("#listCat").children().children().children().children();
	$(a).each( function( index, element ){
		if (searchText == "") {
			$( this ).parent().parent().slideUp();
	    	isEmpty = true;
		}
	    if (reg.test($(this).text().toString()) && !isEmpty) {
	    	$( this ).parent().parent().parent().parent().slideDown();
	    	$( this ).parent().parent().slideDown();
	    	return false;
	    } else if (!isEmpty) {
	    	$( this ).parent().parent().slideUp();
	    }
	});
}

function repairMenu() {
	if (isUsed) {
		$(".nav-second-level").removeAttr('style');
		$(".nav-third-level").removeAttr('style');
	} else {
		$(".nav-second-level").attr('style');
	}
	isUsed = !isUsed;
}

function repairSubMenu() {
	if (isUsed) {
		$(".nav-third-level").removeAttr('style');
	} else {
		$(".nav-second-level").attr('style');
	}
	isUsed = !isUsed;
}