package com.check.demo.dao.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.check.demo.dao.common.entity.BaseEntity;
import com.check.demo.dao.common.model.Pagination;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.MapType;

public abstract class JsonTools
{
	public static final String FILTER_NAME = "myFilter";
	protected static Logger logger = LoggerFactory.getLogger(JsonTools.class);
	protected static ObjectMapper objectMapper;
	static
	{
		objectMapper = new ObjectMapper();
		// 设置日期解析格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// enum用toString方法
		objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		// 属性不存在时不报错
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		// 默认filter
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(FILTER_NAME,
				SimpleBeanPropertyFilter.serializeAllExcept(""));
		objectMapper.setFilters(filterProvider);
	}

	/**
	 * 转换成json字符串，可定义忽略的属性。<BR>
	 * 已经使用{@link JsonIgnore}标注的不用指定也会忽略。<BR>
	 * 被转换的entity需要在类上加{@link JsonFilter}注解
	 * 
	 * @param entity
	 * @param ingoreProperties
	 * @return
	 * @throws IOException
	 */
	public final static String toJsonStr(BaseEntity entity, String... ingoreProperties) throws IOException
	{
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(FILTER_NAME,
				SimpleBeanPropertyFilter.serializeAllExcept(ingoreProperties));
		return objectMapper.writer(filterProvider).writeValueAsString(entity);
	}

	/**
	 * 将entity对象转换成json字符串
	 * 
	 * @param entity
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static String toJsonStr(BaseEntity entity) throws IOException
	{
		return objectMapper.writeValueAsString(entity);
	}

	public final static String toJsonStr(String str)
	{
		try
		{
			return objectMapper.writeValueAsString(str);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public final static String toJsonStr(Number number)
	{
		try
		{
			return objectMapper.writeValueAsString(number);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public final static String toJsonStr(Boolean bool)
	{
		try
		{
			return objectMapper.writeValueAsString(bool);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 将集合对象转换成json字符串
	 * 
	 * @param <T>
	 *            集合内的对象类型必须继承entity
	 * @param collection
	 *            集合
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static <T extends BaseEntity> String toJsonStr(Collection<T> collection) throws IOException
	{
		return objectMapper.writeValueAsString(collection);
	}

	/**
	 * 将Map转换成Json字符串
	 * 
	 * @param map
	 *            map
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static String toJsonStr(Map<?, ?> map) throws IOException
	{
		return objectMapper.writeValueAsString(map);
	}

	/**
	 * 将集合对象转换成json字符串(尽量使用entity格式对象)
	 * 
	 * @param collection
	 *            集合
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static String strArray2JsonStr(Collection<String> collection) throws IOException
	{
		return objectMapper.writeValueAsString(collection);
	}

	/**
	 * 将集合对象转换成json字符串(尽量使用entity格式对象)
	 * 
	 * @param collection
	 *            集合
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static String boolArray2JsonStr(Collection<Boolean> collection) throws IOException
	{
		return objectMapper.writeValueAsString(collection);
	}

	/**
	 * 将集合对象转换成json字符串(尽量使用entity格式对象)
	 * 
	 * @param collection
	 *            集合
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static <T extends Number> String numberArray2JsonStr(Collection<T> collection) throws IOException
	{
		return objectMapper.writeValueAsString(collection);
	}

	/**
	 * 将json字符串转换成entity对象
	 * 
	 * @param <T>
	 *            对象类型
	 * @param jsonStr
	 *            json字符串
	 * @param clazz
	 *            转换后的对象类型，必须是继承BaseEntity的对象
	 * @return 转换后的对象
	 * @throws IOException
	 */
	public final static <T extends BaseEntity> T json2Object(String jsonStr, Class<T> clazz) throws IOException
	{
		if (!StringTools.hasText(jsonStr)) return null;
		return objectMapper.readValue(jsonStr, clazz);
	}

	public final static <E extends BaseEntity> Pagination json2Pagn(String jsonStr, Class<E> resultClazz)
			throws IOException
	{
		if (!StringTools.hasText(jsonStr) || resultClazz == null) return new Pagination();
		int reFirst = jsonStr.indexOf("[");
		int reLast = jsonStr.lastIndexOf("]");
		if (reLast <= reFirst) throw new IllegalArgumentException("不是合法的Pagination Json 字符串.");
		String reStr = jsonStr.substring(reFirst, reLast + 1);
		Pagination pg = objectMapper.readValue(jsonStr, Pagination.class);
		pg.setResult(json2List(reStr, resultClazz));
		return pg;
	}

	/**
	 * 将json字符串转换成集合对象(尽量使用继承BaseEntity的对象)
	 * 
	 * @param <E>
	 *            对象类型
	 * @param jsonStr
	 *            json字符串
	 * @param eleClazz
	 *            转换后的对象类型，必须是继承BaseEntity的对象
	 * @return 转换后的集合
	 * @throws IOException
	 */
	public final static <E> List<E> json2List(String jsonStr, Class<E> eleClazz) throws IOException
	{
		if (!StringTools.hasText(jsonStr) || eleClazz == null) return new ArrayList<E>();
		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, eleClazz);
		return objectMapper.readValue(jsonStr, type);
	}

	/**
	 * json字符串转换成Map对象
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param keyClass
	 *            key的对象类型
	 * @param valueClass
	 *            value的对象类型
	 * @return 转换后的Map
	 * @throws IOException
	 */
	public final static <K, V> Map<K, V> json2Map(String jsonStr, Class<K> keyClass, Class<V> valueClass)
			throws IOException
	{
		if (!StringTools.hasText(jsonStr) || keyClass == null || valueClass == null) return new HashMap<K, V>();
		MapType type = objectMapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
		return objectMapper.readValue(jsonStr, type);
	}
}
