package com.check.demo.dao.common.entity;

import com.alibaba.fastjson.JSON;

public abstract class BaseEntity
{
	public String toString()
	{
		return toJson();
	}

	public final String toJson()
	{
		return JSON.toJSONString(this);
	}

	protected boolean equals(String child, String other)
	{
		return objEquals(child, other);
	}

	protected int hashCode(String id)
	{
		return objHashCode(id);
	}

	protected boolean equals(Long child, Long other)
	{
		return objEquals(child, other);
	}

	protected int hashCode(Long id)
	{
		return objHashCode(id);
	}

	protected boolean equals(Integer child, Integer other)
	{
		return objEquals(child, other);
	}

	protected int hashCode(Integer id)
	{
		return objHashCode(id);
	}

	protected boolean objEquals(Object child, Object other)
	{
		if (child == other) return true;
		if (child == null || other == null) return false;
		return child.equals(other);
	}

	protected int objHashCode(Object id)
	{
		if (id == null) return super.hashCode();
		return id.hashCode();
	}
}
