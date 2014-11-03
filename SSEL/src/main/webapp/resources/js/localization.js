function localization(lang) {
	var currentUrl = window.location.href;
	var isLangParam = false;
	if (window.location.search.substr(1) == "") {
		window.location.replace(currentUrl + "?lang=" + lang);
	} else {
		var parameters = window.location.search.substr(1).split("&");
		for (var i = 0; i < parameters.length; i++) {
			var pair = parameters[i].split("=");
			if (pair[0] == "lang") {
				isLangParam = true;
				pair[1] = lang;
				parameters[i] = pair.join("=");
			}
		}
		if (isLangParam) {
			var newParameters = parameters.join("&");
			var oldParameters = window.location.search.substr(1);
			currentUrl = currentUrl.replace(oldParameters, newParameters);
			window.location.replace(currentUrl);
		} else {
			window.location.replace(currentUrl + "&lang=" + lang);
		}

	}
}