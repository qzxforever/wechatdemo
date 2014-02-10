package com.check.demo.WechatHelper.sdk.message;

import org.springframework.stereotype.Controller;
import org.w3c.dom.Element;

import com.check.demo.WechatHelper.filters.FilterChain;
import com.check.demo.WechatHelper.filters.FilterGreeting;
import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
@Controller
public class MessageHandlerText extends MessageHandlerHelper {
	@Override
	public Message handleSpecialMessage(Message message) {
		FilterChain filterChain = new FilterChain();
			filterChain.addFilter(new FilterGreeting());// add this,so the next line does not have to verify whether result is null or not
		return filterChain.doFilterChain(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {
		MessageText messageText = (MessageText) message;
		messageText.setContent(root.getElementsByTagName(TAG_CONTENT).item(0).getTextContent());
	}

}
