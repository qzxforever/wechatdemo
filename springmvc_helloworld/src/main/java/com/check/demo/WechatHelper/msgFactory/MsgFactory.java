package com.check.demo.WechatHelper.msgFactory;

import com.check.demo.WechatHelper.sdk.IMessage;
import com.check.demo.WechatHelper.sdk.Message;
import com.check.demo.WechatHelper.sdk.message.MessageEvent;
import com.check.demo.WechatHelper.sdk.message.MessageImage;
import com.check.demo.WechatHelper.sdk.message.MessageLink;
import com.check.demo.WechatHelper.sdk.message.MessageLocation;
import com.check.demo.WechatHelper.sdk.message.MessageText;

/**
 * 
 * @author check
 * 2013-12-27
 * email:qzxandgj@aliyun.com
 */
public class MsgFactory implements IMessage {
public String msgType;
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public MsgFactory(String msgType) {
		super();
		this.msgType = msgType;
	}

	public Message create(){
		/*USERMESSAGE uMsg = USERMESSAGE.valueOf(this.msgType);
		switch (uMsg){
		case MESSAGE_EVENT:
			return new MessageEvent();
		case MESSAGE_IMAGE:
			return new MessageImage();
		case MESSAGE_LINK:
			return new MessageLink();
		case MESSAGE_LOCATION:
			return new MessageLocation();
		case MESSAGE_TEXT:
			return new MessageText();
		default:
			return null;
		}*/
		if(this.msgType.equals(MESSAGE_EVENT))
			return new MessageEvent();
		else if (this.msgType.equals(MESSAGE_IMAGE))
			return new MessageImage();
		else if (this.msgType.equals(MESSAGE_LINK))
			return new MessageLink();
		else if (this.msgType.equals(MESSAGE_LOCATION))
			return new MessageLocation();
		else if (this.msgType.equals(MESSAGE_TEXT))
			return new MessageText();
		else 
			return null;
	}
}
