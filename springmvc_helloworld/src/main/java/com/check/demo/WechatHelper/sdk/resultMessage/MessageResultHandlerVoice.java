package com.check.demo.WechatHelper.sdk.resultMessage;

import com.check.demo.WechatHelper.sdk.ItemVoice;
import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageResultHandlerVoice extends MessageResultHandlerHelper {

	@Override
	public String buildMessageResult(Message message) {
		MessageResultVoice messageResultVoice = (MessageResultVoice) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer item = new StringBuffer();
		ItemVoice itemVoice = messageResultVoice.getItemVoice();
		item.append(wrapperContent(TAG_MEDIAID, itemVoice.getMedia_id(), true));
		String image = wrapperContent(TAG_IMAGE, item.toString(), false);
		buffer.append(wrapperContent(TAG_TOUSERNAME, messageResultVoice.getToUserName(), true))
				.append(wrapperContent(TAG_FROMUSERNAME, messageResultVoice.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME, messageResultVoice.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, messageResultVoice.getMsgType(), true)).append(image);
		return wrapperXmlContent(buffer.toString());
	}
}
