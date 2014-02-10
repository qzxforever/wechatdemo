package com.check.demo.WechatHelper.filters;

import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class FilterGreeting extends MessageFilterHelper implements IMessageFilter {

	@Override
	public Message doSpecailMessageFilter(Message message) {
		System.out.println("greeting");
		String temp = "hard code";
		//ResourceManager.getValue("default_greeting")
		return buildMessageResultText(temp);
	}

}
