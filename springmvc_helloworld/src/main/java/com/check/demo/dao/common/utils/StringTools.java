package com.check.demo.dao.common.utils;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


public abstract class StringTools extends StringUtils
{
	protected static Logger logger = LoggerFactory.getLogger(StringTools.class);
	protected static String HOLDER = "`[@`";

	/**
	 * 字符编码变换，ISO8859-1-->UTF-8
	 * 
	 * @param str
	 * @return
	 */
	public static String iso2UTF8(String str)
	{
		try
		{
			if (hasText(str)) return new String(str.getBytes("ISO8859-1"), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error(e.getMessage());
		}
		return "";
	}

	public static String iso2GBK(String str)
	{
		try
		{
			if (hasText(str)) return new String(str.getBytes("ISO8859-1"), "GBK");
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error(e.getMessage());
		}
		return "";
	}

	/**
	 * 将字符串转换为int
	 * 
	 * @param str
	 *            字符串
	 * @param defaultValue
	 *            转换失败返回的默认值
	 * @return 转换成功返回指定值，失败返回默认值
	 */
	public static int str2Int(String str, int defaultValue)
	{
		try
		{
			return (hasText(str)) ? Integer.parseInt(str.trim()) : defaultValue;
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 将字符串转换为long
	 * 
	 * @param str
	 *            字符串
	 * @param defaultValue
	 *            转换失败返回的默认值
	 * @return 转换成功返回指定值，失败返回默认值
	 */
	public static long str2Long(String str, long defaultValue)
	{
		try
		{
			return (hasText(str)) ? Long.parseLong(str.trim()) : defaultValue;
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 将字符串转换为float
	 * 
	 * @param str
	 *            字符串
	 * @param defaultValue
	 *            转换失败返回的默认值
	 * @return 转换成功返回指定值，失败返回默认值
	 */
	public static float str2Float(String str, float defaultValue)
	{
		try
		{
			return (hasText(str)) ? Float.parseFloat(str.trim()) : defaultValue;
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 将字符串转换为double
	 * 
	 * @param str
	 *            字符串
	 * @param defaultValue
	 *            转换失败返回的默认值
	 * @return 转换成功返回指定值，失败返回默认值
	 */
	public static double str2Double(String str, double defaultValue)
	{
		try
		{
			return (hasText(str)) ? Double.parseDouble(str.trim()) : defaultValue;
		}
		catch (Exception e)
		{
			return defaultValue;
		}
	}

	/**
	 * 当前支持输入为111111111111或11:11:11:11:11:11或11-11-11-11-11-11形式的mac地址
	 * 
	 * @param mac
	 * @return 123456789abc
	 */
	public static String toMac12(String mac)
	{
		checkMac(mac);
		String result = mac.replace(":", "");
		result = result.replace("-", "");
		return result;
	}

	/**
	 * 当前支持输入为111111111111或11:11:11:11:11:11或11-11-11-11-11-11形式的mac地址
	 * 
	 * @param mac
	 * @return 11:11:11:11:11:11
	 */
	public static String toMacUnix(String mac)
	{
		checkMac(mac);
		String result = mac.trim();
		if (result.contains(":")) return result;
		if (result.contains("-")) return result.replace("-", ":");
		byte[] bytes = result.getBytes();
		if (bytes.length != 12) return result;
		byte[] tmp = new byte[17];
		for (int i = 0; i < 17; i++)
		{
			int k = i / 3;
			if ((i + 1) % 3 == 0) tmp[i] = ':';
			else tmp[i] = bytes[i - k];
		}
		return new String(tmp);
	}

	/**
	 * 当前支持输入为111111111111或11:11:11:11:11:11或11-11-11-11-11-11形式的mac地址
	 * 
	 * @param mac
	 * @return 12-34-56-78-90-ab
	 */
	public static String toMacWin(String mac)
	{
		checkMac(mac);
		String result = mac.trim();
		if (result.contains("-")) return result;
		if (result.contains(":")) return result.replace(":", "-");
		byte[] bytes = result.getBytes();
		if (bytes.length != 12) return result;
		byte[] tmp = new byte[17];
		for (int i = 0; i < 17; i++)
		{
			int k = i / 3;
			if ((i + 1) % 3 == 0) tmp[i] = '-';
			else tmp[i] = bytes[i - k];
		}
		return new String(tmp);
	}

	private static void checkMac(String mac)
	{
		boolean rs;
		if (!StringTools.hasText(mac)) rs = false;
		rs = PatternFactory.getMacPattern().matcher(mac).matches();
		if (!rs) throw new IllegalArgumentException("Not a mac address.");
	}

	public static boolean contains(String[] strs, String search)
	{
		if (strs == null || strs.length <= 0) return false;
		for (String str : strs)
		{
			if (str.equals(search)) return true;
		}
		return false;
	}

	public static String float2Str(Float f)
	{
		return (f == null) ? "" : f.toString();
	}

	public static String integer2Str(Integer i)
	{
		return (i == null) ? "" : i.toString();
	}

	public static String getFileNameByPath(String filePath)
	{
		if (!StringTools.hasText(filePath)) return filePath;
		return filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
	}

	/**
	 * 去除前后的指定字符
	 * 
	 * @param str
	 * @param c
	 * @return
	 */
	public static String trim(String str, char c)
	{
		String trimLeading = trimLeadingCharacter(str, c);
		return trimTrailingCharacter(trimLeading, c);
	}

	/**
	 * 将使用{}占位符的字符串转成日志所需要的字符串
	 * 
	 * @param log
	 *            日志信息
	 * @param args
	 *            占位符参数（依次替换占位符,比实际占位符多则取前占位符个数个，少则后面的占位符不替换）
	 * @return 使用占位符替换后的字符串
	 */
	public static String toLogString(String log, Object... args)
	{
		if (!hasText(log) || args == null) return log;
		String rs = log;
		for (Object arg : args)
		{
			String rm = arg == null ? "null" : arg.toString();
			if (arg != null) rm = rm.replace("{}", HOLDER);
			rs = rs.replaceFirst("\\{\\}", rm);
		}
		return rs.replace(HOLDER, "{}");
	}
}
