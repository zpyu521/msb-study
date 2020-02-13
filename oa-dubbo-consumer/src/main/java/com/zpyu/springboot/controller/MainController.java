package com.zpyu.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpyu.springboot.config.Config;

@Controller
@RequestMapping("/oa")
public class MainController {
	
	@Autowired
	private Config config;
	
	@RequestMapping(value = {"", "index" })
	public Object index(Model model) {
		model.addAttribute("systemName",config.getSystemName());
		return "index";
	}
}
