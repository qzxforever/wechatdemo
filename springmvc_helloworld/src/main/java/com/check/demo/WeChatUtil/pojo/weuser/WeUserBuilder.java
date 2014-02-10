package com.check.demo.WeChatUtil.pojo.weuser;


public class WeUserBuilder {
	String appId;
	String appSecret;
	String open_id;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	public WeUserBuilder(String appId, String appSecret, String open_id) {
		super();
		this.appId = appId;
		this.appSecret = appSecret;
		this.open_id = open_id;
	}

	/*public WeChatCustomer build(){
		WeChatCustomer weChatCustomer = new WeChatCustomer();
		AccessToken at = WeixinUtil.getAccessToken(this.getAppId(),this.getAppSecret());
		if (null != at) {
			// ��ȡ�б�
			weChatCustomer = WeixinUtil.getUserInfo(at.getToken(), open_id);
		}
		return weChatCustomer;
	}*/
	
}
