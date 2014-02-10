package com.check.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloworldController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/springmvc/helloworld", method = RequestMethod.GET)
	public String helloworld(Model model) {
		model.addAttribute("msg", "hello everybody");
		return "helloworld";
	}
}
