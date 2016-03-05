package com.xianda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xianda.annotation.Layout;


@Controller
@RequestMapping("/")
class HomeController {
	@Layout(value = "blank")
	@RequestMapping(method = RequestMethod.GET)
	String index() {
		return "home";
	}
}