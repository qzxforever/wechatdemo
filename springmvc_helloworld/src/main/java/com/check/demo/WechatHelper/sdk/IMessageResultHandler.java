package com.check.demo.WechatHelper.sdk;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public interface IMessageResultHandler {

	// build the content of message result
	public String buildMessageResult(Message message);

}
