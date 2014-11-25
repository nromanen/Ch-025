/*!
 * Scripts to admin part
 */

// Search
function setIconClassFunction(nextClass) {
	var deleteClass = "glyphicon glyphicon-remove red";
	var searchClass = "glyphicon glyphicon-search";
	 if (nextClass == "delete") {
		 $("#searchIcon").removeClass().addClass(deleteClass);
			$("#searchIcon").attr("onclick","clearTextFunction()");
	 } else {
	 $("#searchIcon").removeClass().addClass(searchClass);
	$("#searchIcon").attr("onclick","searchTextFunction()");
	 }
}

function clearTextFunction() {
	 $('#searchText').val("");
	 setIconClassFunction("search");
	 $("#searchIcon").click();
}

// /Search