package com.check.demo.WechatHelper.sdk.message;

import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

import com.check.demo.WechatHelper.sdk.IMessage;
import com.check.demo.WechatHelper.sdk.IMessageHadler;
import com.check.demo.WechatHelper.sdk.ItemArticle;
import com.check.demo.WechatHelper.sdk.ItemMusic;
import com.check.demo.WechatHelper.sdk.Message;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultMusic;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultNews;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultText;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public abstract class MessageHandlerHelper implements IMessageHadler, IMessage {

	protected Message message;

	// subclass implements it
	protected abstract void parseSpecialMessage(Message message, Element root);

	protected abstract Message handleSpecialMessage(Message message);

	// put common parse part here!

	public void parseMessage(Message message, Element root) {
		message.setMsgType(root.getElementsByTagName(TAG_MSGTYPE).item(0).getTextContent());
		message.setFromUserName((root.getElementsByTagName(TAG_FROMUSERNAME)).item(0).getTextContent());
		message.setToUserName((root.getElementsByTagName(TAG_TOUSERNAME)).item(0).getTextContent());
		message.setCreateTime((root.getElementsByTagName(TAG_CREATETIME)).item(0).getTextContent());
		//message.setMsgId((root.getElementsByTagName(TAG_MSGID)).item(0).getTextContent());//seems to be wrong!
		parseSpecialMessage(message, root);
	}


	public Message handleMessage(Message message) {
		this.message = message;
		return handleSpecialMessage(message);
	}

	// build result message text
	protected Message buildMessageResultText(String content) { 
		MessageResultText messageResultText = new MessageResultText();
		messageResultText.setMsgType(MESSAGE_RESULT_TEXT);
		messageResultText.setContent(content);
		messageResultText.setFromUserName(message.getToUserName());
		messageResultText.setToUserName(message.getFromUserName());
		messageResultText.setFuncFlag(1);//
		messageResultText.setCreateTime(Integer.toString(getCurrentUnixTimestamp()));
		return messageResultText;
	}

	// build result message news
	protected Message buildMessageResultNews(List<ItemArticle> items) {
		MessageResultNews messageResultNews = new MessageResultNews();
		messageResultNews.setMsgType(MESSAGE_RESULT_NEWS);
		messageResultNews.setCreateTime(Integer.toString(getCurrentUnixTimestamp()));
		messageResultNews.setFromUserName(message.getToUserName());
		messageResultNews.setToUserName(message.getFromUserName());
		messageResultNews.setArticleItems(items);
		messageResultNews.setCount(items.size());
		messageResultNews.setFuncFlag(1);//
		return messageResultNews;
	}

	// build result message news
	protected Message buildMessageResultMusic(ItemMusic item) {
		MessageResultMusic messageResultMusic = new MessageResultMusic();
		messageResultMusic.setMsgType(MESSAGE_RESULT_MUSIC);
		messageResultMusic.setCreateTime(Integer.toString(getCurrentUnixTimestamp()));
		messageResultMusic.setFromUserName(message.getToUserName());
		messageResultMusic.setToUserName(message.getFromUserName());
		messageResultMusic.setMusic(item);
		messageResultMusic.setFuncFlag(1);//
		return messageResultMusic;
	}

	// get current Unix time
	public int getCurrentUnixTimestamp() {
		Date date = new Date();
		return (int) (date.getTime() / 1000);
	}

}
