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
public class MessageHandlerLink extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new FilterGreeting());// add this,so the next line does not have to verify whether result is null or not
		return filterChain.doFilterChain(message);	
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageLink messageLink = (MessageLink) message;
		messageLink.setUrl(root.getElementsByTagName(TAG_URL).item(0).getTextContent());
		messageLink.setDescription(root.getElementsByTagName(TAG_DESCRIPTION).item(0).getTextContent());
		messageLink.setTitle(root.getElementsByTagName(TAG_TITLE).item(0).getTextContent());
	}

}
