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
public class MessageHandlerLocation extends MessageHandlerHelper {

	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
		filterChain.addFilter(new FilterGreeting());
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageLocation messageLocation = (MessageLocation) message;
		messageLocation.setLocationX(root.getElementsByTagName(TAG_LOCATIONX).item(0).getTextContent());
		messageLocation.setLocationY(root.getElementsByTagName(TAG_LOCATIONY).item(0).getTextContent());
		messageLocation.setLabel(root.getElementsByTagName(TAG_LABEL).item(0).getTextContent());
		//messageLocation.setScale(root.getElementsByTagName(TAG_SCALE).item(0).getTextContent());
	}

}
