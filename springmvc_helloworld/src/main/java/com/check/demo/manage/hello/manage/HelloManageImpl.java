package com.check.demo.manage.hello.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.check.demo.dao.hello.dao.HelloDao;
import com.check.demo.dao.hello.dao.Person;
import com.check.demo.manage.common.manage.AbstractManager;

@Service("helloManage")
public class HelloManageImpl extends  AbstractManager implements HelloManage {
	@Autowired
	private HelloDao helloDao;

	@Override
	public String hello(String username) {
		return "sayHello" + username;
	}

	@Override
	@Transactional
	public void save(Person person) {
		helloDao.saveperson(person);
	}

	@Override
	public Person findById(String id) {
		return helloDao.findById(id);
	}

}
