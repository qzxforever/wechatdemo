package com.check.demo.dao.hello.dao;

import com.check.demo.dao.common.JpaDao.AbstractJpaDao;


public interface HelloDao extends AbstractJpaDao<Person>{
	public void saveperson(Person person);
	
	public Person findById(String id);
}
