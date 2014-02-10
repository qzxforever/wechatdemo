package com.check.demo.WechatHelper.sdk.message;

import org.w3c.dom.Element;

import com.check.demo.WechatHelper.filters.FilterChain;
import com.check.demo.WechatHelper.sdk.Message;


/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageHandlerEvent extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageEvent messageEvent = (MessageEvent) message;
		messageEvent.setEvent(root.getElementsByTagName(TAG_EVENT).item(0).getTextContent());
		messageEvent.setEventKey(root.getElementsByTagName(TAG_EVENTKEY).item(0).getTextContent());
	}

}
