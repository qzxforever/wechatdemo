package com.check.demo.WechatHelper.sdk.resultMessage;

import com.check.demo.WechatHelper.sdk.ItemVideo;
import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageResultHandlerVideo extends MessageResultHandlerHelper {


	@Override
	public String buildMessageResult(Message message) {
		MessageResultVideo messageResultVideo = (MessageResultVideo) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer item = new StringBuffer();
		ItemVideo itemVideo = messageResultVideo.getItemVideo();
		item.append(wrapperContent(TAG_MEDIAID, itemVideo.getMedia_id(), true))
			.append(wrapperContent(TAG_VIDEOTITLE, itemVideo.getTitle(), true))
			.append(wrapperContent(TAG_VIDEODESCRIPTION, itemVideo.getDescription(), true));
		String image = wrapperContent(TAG_IMAGE, item.toString(), false);
		buffer.append(wrapperContent(TAG_TOUSERNAME, messageResultVideo.getToUserName(), true))
				.append(wrapperContent(TAG_FROMUSERNAME, messageResultVideo.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME, messageResultVideo.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, messageResultVideo.getMsgType(), true)).append(image);
		return wrapperXmlContent(buffer.toString());
	}

}
