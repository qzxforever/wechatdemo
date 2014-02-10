package com.check.demo.dao.common.JpaDaoImpl;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.CollectionUtils;

/**
 * @author Peream<br>
 *         邮箱：peream@gmail.com<br>
 *         创建日期：2008-2-29 下午04:22:48
 * @since 1.0
 * @version 1.0
 */
public abstract class CollectionTools extends CollectionUtils
{
	public static <K, V> HashMap<K, V> newHashMap()
	{
		return new HashMap<K, V>();
	}

	public static <K, V> ConcurrentHashMap<K, V> newConcurrentHashMap()
	{
		return new ConcurrentHashMap<K, V>();
	}
}
