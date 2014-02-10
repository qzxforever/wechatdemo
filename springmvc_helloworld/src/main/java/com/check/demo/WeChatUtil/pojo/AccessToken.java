package com.check.demo.WeChatUtil.pojo;
/**
 * ΢��ͨ�ýӿ�ƾ֤
 * 
 * @author check
 * @date 2013-12-27
 */
public class AccessToken {
	// ��ȡ����ƾ֤
	private String token;
	// ƾ֤��Чʱ�䣬��λ����
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}