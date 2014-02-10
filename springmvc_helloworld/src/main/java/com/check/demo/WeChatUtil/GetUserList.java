package com.check.demo.WeChatUtil;

import java.util.List;

import com.check.demo.WeChatUtil.pojo.AccessToken;

public class GetUserList {

	public static void main(String[] args) {
		// �����û�Ψһƾ֤
		String appId = "wx077c2c54550a0237";
		// �����û�Ψһƾ֤��Կ
		String appSecret = "04d1c4195dbb6ae1d959597ab1597b09";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		if (null != at) {
			// ��ȡ�б�
			List<String> result = WeixinUtil.getUserList(at.getToken(), null);
			
		}
		
	}

}
