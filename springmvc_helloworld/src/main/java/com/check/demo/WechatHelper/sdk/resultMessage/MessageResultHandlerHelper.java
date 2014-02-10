package com.check.demo.WechatHelper.sdk.resultMessage;

import com.check.demo.WechatHelper.sdk.IMessage;
import com.check.demo.WechatHelper.sdk.IMessageResultHandler;
import com.check.demo.WechatHelper.sdk.Message;



/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageResultHandlerHelper implements IMessage,
		IMessageResultHandler {
	
	public String buildMessageResult(Message message) {
		return null;
	}

	// wrap the content by tag & content & whether CDATA used
	public String wrapperContent(String tag, String content, boolean isCdata) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<" + tag + ">");
		if (isCdata) {
			buffer.append("<![CDATA[");
		}
		buffer.append(content);
		if (isCdata) {
			buffer.append("]]>");
		}
		buffer.append("</" + tag + ">");
		return buffer.toString();
	}

	// wrap the root tag xml
	public String wrapperXmlContent(String content) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<" + TAG_XML + ">");
		buffer.append(content);
		buffer.append("</" + TAG_XML + ">");
		return buffer.toString();
	}

}
