package com.check.demo.WechatHelper.sdk.message;

import org.w3c.dom.Element;

import com.check.demo.WechatHelper.filters.FilterChain;
import com.check.demo.WechatHelper.filters.FilterGreeting;
import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageHandlerImage extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new FilterGreeting());// add this,so the next line does not have to verify whether result is null or not
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageImage messageImage = (MessageImage) message;
		messageImage.setPicUrl(root.getElementsByTagName(TAG_PICURL).item(0).getTextContent());
	}

}
