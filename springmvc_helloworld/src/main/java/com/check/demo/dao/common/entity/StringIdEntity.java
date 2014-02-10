package com.check.demo.dao.common.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class StringIdEntity extends BaseEntity 
{
	protected String id;

	@Id
	@Column(name = "id")
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof StringIdEntity)) return false;
		StringIdEntity other = (StringIdEntity) obj;
		return super.equals(id, other.getId());
	}

	@Override
	public int hashCode()
	{
		return super.hashCode(id);
	}

}
