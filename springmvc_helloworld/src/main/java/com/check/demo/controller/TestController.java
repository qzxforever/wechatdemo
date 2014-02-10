package com.check.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.check.demo.dao.hello.dao.Person;
import com.check.demo.manage.hello.manage.HelloManage;
@Controller
public class TestController {
	@Autowired
	private HelloManage helloManage;
	
	@RequestMapping(value = "/mywelcome", method = RequestMethod.GET)
	public String registPost() {
		return "/welcome";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String savePerson() {
		Person person = new Person();
		person.setId("3333");
		person.setName("qzx");
		person.setPasswd("qzx");
		helloManage.save(person);
		return "/welcome";
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String findPerson() {
		System.out.println("url匹配成功。。。。");
		String id = "0001";
		Person person = helloManage.findById(id);
		System.out.println(person.toString());
		return "/welcome";
	}

}
