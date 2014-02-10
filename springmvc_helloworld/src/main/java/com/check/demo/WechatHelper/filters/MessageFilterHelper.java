package com.check.demo.WechatHelper.filters;

import org.w3c.dom.Element;

import com.check.demo.WechatHelper.sdk.Message;
import com.check.demo.WechatHelper.sdk.message.MessageHandlerHelper;



/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public abstract class MessageFilterHelper extends MessageHandlerHelper implements IMessageFilter {

	// subclass handle it
	public abstract Message doSpecailMessageFilter(Message message);

	public Message doMessageFilter(Message message) {
		this.message = message;// must do this! otherwise, message may be null
		return doSpecailMessageFilter(message);
	}

	@Override
	protected void parseSpecialMessage(Message message, Element root) {// do nothing

	}

	@Override
	protected Message handleSpecialMessage(Message message) {// do nothing
		return null;
	}

}
