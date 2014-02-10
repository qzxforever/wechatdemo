package com.check.demo.WechatHelper.sdk.resultMessage;

import com.check.demo.WechatHelper.sdk.ItemArticle;
import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageResultHandlerNews extends MessageResultHandlerHelper {

	@Override
	public String buildMessageResult(Message message) {
		MessageResultNews messageResultNews = (MessageResultNews) message;
		StringBuffer buffer = new StringBuffer();
		StringBuffer items = new StringBuffer();
		StringBuffer item = new StringBuffer();
		ItemArticle itemArticle = null;
		for (int i = 0; i < messageResultNews.getCount(); i++) {
			item = new StringBuffer();// important!easy to miss
			itemArticle = messageResultNews.getArticleItems().get(i);
			item.append(wrapperContent(TAG_TITLE, itemArticle.getTitle(), true))
					.append(wrapperContent(TAG_DESCRIPTION, itemArticle.getDescription(), true))
					.append(wrapperContent(TAG_PICURL, itemArticle.getPicUrl(), true))
					.append(wrapperContent(TAG_URL, itemArticle.getUrl(), true));
			items.append(wrapperContent(TAG_ITEM, item.toString(), false));
		}
		String articles = wrapperContent(TAG_ARTICLES, items.toString(), false);
		buffer.append(wrapperContent(TAG_TOUSERNAME, messageResultNews.getToUserName(), true))
				.append(wrapperContent(TAG_FROMUSERNAME, messageResultNews.getFromUserName(), true))
				.append(wrapperContent(TAG_CREATETIME, messageResultNews.getCreateTime() + "", false))
				.append(wrapperContent(TAG_MSGTYPE, messageResultNews.getMsgType(), true))
				.append(wrapperContent(TAG_ARTICLECOUNT, messageResultNews.getCount() + "", false)).append(articles)
				.append(wrapperContent(TAG_FUNCFLAG, messageResultNews.getFuncFlag() + "", false));
		return wrapperXmlContent(buffer.toString());
	}

}
