package com.softserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class CustomErrorController {

	@RequestMapping("error")
	public String customError() {
		return "customError";
	}

}