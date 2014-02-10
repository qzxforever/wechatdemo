package com.check.demo.WechatHelper.sdk;

import org.w3c.dom.Element;
/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public interface IMessageHadler {

	// handle message, and return the message result
	public void parseMessage(Message message, Element element);

	// handle message, and return the message result
	public Message handleMessage(Message message);

}
