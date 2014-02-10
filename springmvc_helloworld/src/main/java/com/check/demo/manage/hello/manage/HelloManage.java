package com.check.demo.manage.hello.manage;

import com.check.demo.dao.hello.dao.Person;

public interface HelloManage {
	
	public String hello(String username);
	
	public void save(Person person);
	
	public Person findById(String id);
}
