package com.check.demo.WechatHelper.sdk;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class Message implements IMessage {

	protected String msgType;
	protected String fromUserName;
	protected String toUserName;
	// attention! class MessageHandlerHelper its method parseMessage
	// I do not parse msgId and createtime,because it seems to be meaningless
	protected String msgId;// 64bits for message result and event message, this field is useless
	protected String createTime;// 32bits the time is Unix timestamp

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
