package com.check.demo.WechatHelper.sdk.resultMessage;

import com.check.demo.WechatHelper.sdk.ItemVoice;
import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageResultVoice extends Message {

	protected ItemVoice itemVoice;

	public ItemVoice getItemVoice() {
		return itemVoice;
	}

	public void setItemVoice(ItemVoice itemVoice) {
		this.itemVoice = itemVoice;
	}
}
