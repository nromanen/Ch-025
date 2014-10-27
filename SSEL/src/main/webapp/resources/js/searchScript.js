function searchSubjects() {
	var searchText = $("#searchInput").val();
	var reg = new RegExp(searchText + "/i");
	var li = $("#listSubj").children().children().children().children();
	$(li).each( function( index, element ){
		console.log($(this).text().toString());
	    if (reg.test($(this).text().toString())) {
	    	console.log("find");
	    	//console.log($( element ).text());
	    	//$( this ).parent().parent().parent().parent().slideDown();
	    	//return false;
	    	//TODO bug with regexp and toggle
	    } else {
	    	console.log("not find");
	    	//console.log($( this ).text());
	    	//$( this ).parent().parent().parent().parent().slideUp();
	    	//return false;
	    }
	});
}