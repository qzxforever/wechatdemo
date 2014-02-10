package com.check.demo.WeChatUtil;

import com.check.demo.WeChatUtil.pojo.AccessToken;
public class getUserInfo {
	public static void main(String[] args) {
		// �����û�Ψһƾ֤
		String appId = "wx077c2c54550a0237";
		// �����û�Ψһƾ֤��Կ
		String appSecret = "04d1c4195dbb6ae1d959597ab1597b09";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		//System.out.println(at.getToken());
		if (null != at) {
			/*List<String> result = WeixinUtil.getUserList(at.getToken(), null);
			// ��ȡ�б�
			for(String id : result){
				WeixinUtil.getUserInfo(at.getToken(), id);
			}
			*/
			
			WeixinUtil.getUserInfo(at.getToken(),"oBffVt0Aoe1LZ-9PucryOjMN0R44");
			
		}
		
	}
}
