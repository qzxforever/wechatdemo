package com.check.demo.dao.hello.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.check.demo.dao.common.JpaDaoImpl.MyBaseDao;

@Repository("helloDao")
public class HelloDaoImpl extends MyBaseDao<Person> implements HelloDao{
	
	
	@Transactional
	public void saveperson(Person person) {
		super.persist(person);
	} 


	@Override
	@Transactional
	public Person findById(String id) {
		Person person = super.findById(id);
        return person;
	}

}
