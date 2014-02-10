package com.check.demo.WechatHelper.sdk.resultMessage;

import java.util.List;

import com.check.demo.WechatHelper.sdk.ItemArticle;
import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageResultNews extends Message {

	protected int funcFlag;
	protected int count;
	protected List<ItemArticle> articleItems;

	public int getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ItemArticle> getArticleItems() {
		return articleItems;
	}

	public void setArticleItems(List<ItemArticle> articleItems) {
		this.articleItems = articleItems;
	}

}
