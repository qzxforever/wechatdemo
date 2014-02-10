package com.check.demo.WechatHelper.filters;

import com.check.demo.WechatHelper.sdk.Message;



/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public interface IMessageFilter {

	public Message doMessageFilter(Message message);// message come from

}
