package com.check.demo.dao.hello.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.check.demo.dao.common.entity.StringIdEntity;

@Entity
@Table(name = "person")
public class Person extends StringIdEntity implements Serializable{
	private static final long serialVersionUID = 6526035900545523037L;
	private String name;
	private String passwd;
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "passwd")
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
