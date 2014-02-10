package com.check.demo.WechatHelper.msgFactory;

import com.check.demo.WechatHelper.sdk.IMessage;
import com.check.demo.WechatHelper.sdk.message.MessageHandlerEvent;
import com.check.demo.WechatHelper.sdk.message.MessageHandlerHelper;
import com.check.demo.WechatHelper.sdk.message.MessageHandlerImage;
import com.check.demo.WechatHelper.sdk.message.MessageHandlerLink;
import com.check.demo.WechatHelper.sdk.message.MessageHandlerLocation;
import com.check.demo.WechatHelper.sdk.message.MessageHandlerText;

public class MsgHandlerFactory implements IMessage {
public String msgType;
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public MsgHandlerFactory(String msgType) {
		super();
		this.msgType = msgType;
	}

	public MessageHandlerHelper create(){
		//USERMESSAGE uMsg = USERMESSAGE.valueOf(this.msgType);
		/*switch (uMsg){
		case MESSAGE_EVENT:
			return new MessageHandlerEvent();
		case MESSAGE_IMAGE:
			return new MessageHandlerImage();
		case MESSAGE_LINK:
			return new MessageHandlerLink();
		case MESSAGE_LOCATION:
			return new MessageHandlerLocation();
		case MESSAGE_TEXT:
			return new MessageHandlerText();
		default:
			return null;
		}*/
		if(this.msgType.equals(MESSAGE_EVENT))
			return new MessageHandlerEvent();
		else if (this.msgType.equals(MESSAGE_IMAGE))
			return new MessageHandlerImage();
		else if (this.msgType.equals(MESSAGE_LINK))
			return new MessageHandlerLink();
		else if (this.msgType.equals(MESSAGE_LOCATION))
			return new MessageHandlerLocation();
		else if (this.msgType.equals(MESSAGE_TEXT))
			return new MessageHandlerText();
		else 
			return null;
	}
}
