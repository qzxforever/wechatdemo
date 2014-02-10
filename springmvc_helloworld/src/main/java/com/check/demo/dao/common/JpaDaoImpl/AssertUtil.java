package com.check.demo.dao.common.JpaDaoImpl;

import org.springframework.util.Assert;

public abstract class AssertUtil extends Assert
{
	public static void has2Elements(Object[] arrays)
	{
		has2Elements(arrays, "arrays must has 2 elements.");
	}

	public static void has2Elements(Object[] arrays, String message)
	{
		if (arrays == null || arrays.length != 2) throw new IllegalArgumentException(message);
	}

	public static void hasNElements(int n, Object[] arrays)
	{
		hasNElements(n, arrays, "arrays must has " + n + " elements.");
	}

	public static void hasNElements(int n, Object[] arrays, String message)
	{
		if (arrays == null || arrays.length != n) throw new IllegalArgumentException(message);
	}

}
