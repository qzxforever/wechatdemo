package com.check.demo.WechatHelper.sdk;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
@XmlRootElement
public class ItemArticle {

	private String title;
	private String description;
	private String picUrl;
	private String url;
	private String openID;

	public ItemArticle() {
	}

	public ItemArticle(String title, String description, String picUrl, String url) {
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

}
