package com.check.demo.WechatHelper.msgFactory;

import com.check.demo.WechatHelper.sdk.IMessage;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultHandlerHelper;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultHandlerImage;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultHandlerMusic;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultHandlerNews;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultHandlerText;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultHandlerVideo;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultHandlerVoice;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MsgResultFactory implements IMessage{
	public String msgType;
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public MsgResultFactory(String msgType) {
		super();
		this.msgType = msgType;
	}

	public MessageResultHandlerHelper create(){
		//PLATFROMMESSAGE uMsg = PLATFROMMESSAGE.valueOf(this.msgType);
		/*switch (uMsg){
		case MESSAGE_RESULT_MUSIC:
			return new MessageResultHandlerMusic();
		case MESSAGE_RESULT_NEWS:
			new MessageResultHandlerNews();
		case MESSAGE_RESULT_TEXT:
			return new MessageResultHandlerText();
		default:
			return null;
		}*/
		if(this.msgType.equals(MESSAGE_RESULT_MUSIC))
			return new MessageResultHandlerMusic();
		else if (this.msgType.equals(MESSAGE_RESULT_NEWS))
			return new MessageResultHandlerNews();
		else if (this.msgType.equals(MESSAGE_RESULT_TEXT))
			return new MessageResultHandlerText();
		else if(this.msgType.equals(MESSAGE_RESULT_IMAGE))
			return new MessageResultHandlerImage();
		else if (this.msgType.equals(MESSAGE_RESULT_VOICE))
			return new MessageResultHandlerVoice();
		else if (this.msgType.equals(MESSAGE_RESULT_VIDEO))
			return new MessageResultHandlerVideo();
		else 
			return null;
	}
}
