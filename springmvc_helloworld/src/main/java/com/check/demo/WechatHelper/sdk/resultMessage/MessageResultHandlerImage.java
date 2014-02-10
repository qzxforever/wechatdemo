package com.check.demo.WechatHelper.sdk.resultMessage;

import com.check.demo.WechatHelper.sdk.ItemImage;
import com.check.demo.WechatHelper.sdk.Message;



/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageResultHandlerImage extends MessageResultHandlerHelper {

	@Override
	public String buildMessageResult(Message message) {
		MessageResultImage messageResultImage = (MessageResultImage) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer item = new StringBuffer();
		ItemImage itemImage = messageResultImage.getItemImage();
		item.append(wrapperContent(TAG_MEDIAID, itemImage.getMedia_id(), true));
		String image = wrapperContent(TAG_IMAGE, item.toString(), false);
		buffer.append(wrapperContent(TAG_TOUSERNAME, messageResultImage.getToUserName(), true))
				.append(wrapperContent(TAG_FROMUSERNAME, messageResultImage.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME, messageResultImage.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, messageResultImage.getMsgType(), true)).append(image);
		return wrapperXmlContent(buffer.toString());
	}
}
