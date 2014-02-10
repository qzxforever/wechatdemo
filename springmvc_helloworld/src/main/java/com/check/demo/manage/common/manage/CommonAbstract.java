package com.check.demo.manage.common.manage;


import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.check.demo.dao.common.JpaDaoImpl.CollectionTools;
import com.check.demo.dao.common.JpaDaoImpl.ObjectTools;
import com.check.demo.dao.common.utils.StringTools;

public abstract class CommonAbstract
{
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @see {@link StringTools#toLogString(String, Object...)}
	 */
	protected static final String toLogString(String str, Object... args)
	{
		return StringTools.toLogString(str, args);
	}

	protected static final boolean hasText(String str)
	{
		return StringTools.hasText(str);
	}

	protected static final boolean isEmpty(Object[] array)
	{
		return ObjectTools.isEmpty(array);
	}

	protected static final boolean isEmpty(Collection<?> collection)
	{
		return CollectionTools.isEmpty(collection);
	}

	protected static final boolean isEmpty(Map<?, ?> map)
	{
		return CollectionTools.isEmpty(map);
	}
}
