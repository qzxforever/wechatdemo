package com.check.demo.dao.common.utils;

import java.util.regex.Pattern;

public abstract class PatternFactory
{
	private static Pattern intPattern;
	private static Pattern emailPattern;
	private static Pattern checkBoxPattern;
	private static Pattern intScopePattern;
	private static Pattern phonePattern;
	private static Pattern urlPattern;
	private static Pattern mobilePattern;
	private static Pattern macPattern;

	/**
	 * MAC的正则
	 * 
	 * @return
	 */
	public static Pattern getMacPattern()
	{
		if (macPattern == null)
			macPattern = Pattern
					.compile("(([0-9a-fA-F]{2})(-[0-9a-fA-F]{2}){5})|(([0-9a-fA-F]{2})(:[0-9a-fA-F]{2}){5})|(([0-9a-fA-F]{2})([0-9a-fA-F]{2}){5})");
		return macPattern;
	}

	/**
	 * 整数的正则
	 * 
	 * @return
	 */
	public static Pattern getIntPattern()
	{
		if (intPattern == null) intPattern = Pattern.compile("^(\\-)?[0-9]{1,19}$");
		return intPattern;
	}

	/**
	 * Email 正则
	 * 
	 * @return
	 */
	public static Pattern getEmailPattern()
	{
		if (emailPattern == null)
			emailPattern = Pattern
					.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		return emailPattern;
	}

	/**
	 * checkBox正则
	 * 
	 * @return
	 */
	public static Pattern getCheckBoxPattern()
	{
		if (checkBoxPattern == null)
			checkBoxPattern = Pattern.compile("yes|no", Pattern.CASE_INSENSITIVE);
		return checkBoxPattern;
	}

	/**
	 * 整数范围正则如:2-90
	 * 
	 * @return
	 */
	public static Pattern getIntScopePattern()
	{
		if (intScopePattern == null)
			intScopePattern = Pattern.compile("[0-9]{1,10}\\-[0-9]{1,10}");
		return intScopePattern;
	}

	/**
	 * 电话号码正则
	 * 
	 * @return
	 */
	public static Pattern getPhonePattern()
	{
		if (phonePattern == null)
		{
			String regex = "^[+]{0,1}(\\d){1,3}[ ]?([-]?((\\d)|[ ]){1,12})+$";
			phonePattern = Pattern.compile(regex);
		}
		return phonePattern;
	}

	/**
	 * 手机号码正则
	 * 
	 * @return
	 */
	public static Pattern getMobilePattern()
	{
		if (mobilePattern == null)
		{
			String regex = "^[+]{0,1}(\\d){1,3}[ ]?([-]?((\\d)|[ ]){1,12})+$";
			mobilePattern = Pattern.compile(regex);
		}
		return mobilePattern;
	}

	/**
	 * URL正则
	 * 
	 * @return
	 */
	public static Pattern getUrlPattern()
	{
		if (urlPattern == null)
		{
			String regex = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
			urlPattern = Pattern.compile(regex);
		}
		return urlPattern;
	}

	private static Pattern userNamePattern;

	/**
	 * 用户名正则
	 * 
	 * @return
	 */
	public static Pattern getUserNamePattern()
	{
		if (userNamePattern == null)
		{
			String regex = "^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$";
			userNamePattern = Pattern.compile(regex);
		}
		return userNamePattern;
	}
}
