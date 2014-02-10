package com.check.demo.WeChatUtil;

import com.check.demo.WeChatUtil.pojo.AccessToken;
import com.check.demo.WeChatUtil.pojo.serviceMessage.Msg;
import com.check.demo.WeChatUtil.pojo.serviceMessage.Text;
import com.check.demo.WeChatUtil.pojo.serviceMessage.TextMsg;

/**
 * @author check
 * 2013-12-30
 * email:qzxandgj@aliyun.com
 */
public class MessageManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// �����û�Ψһƾ֤
		String appId = "wx424f5d02bc8da529";
		// �����û�Ψһƾ֤��Կ
		String appSecret = "c697cf01c9445ae0fe886bc831aab812";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		System.out.println(at.getToken());
		if (null != at) {
			// ���ýӿڴ����˵�
			int result = WeixinUtil.send(getMsg(), at.getToken());

			// �жϲ˵��������
			if (0 == result)
				System.out.println("��Ϣ�ظ��ɹ���");
			else
				System.out.println("��Ϣ�ظ�ʧ�ܣ������룺" + result);
		}

	}
	
	/**
	 * ��װ�˵����
	 * 
	 * @return
	 */
	private static Msg getMsg() {
		Text text = new Text();
		text.setContent("hello world");
		TextMsg textMsg = new TextMsg();
		textMsg.setTouser("oH5eIjo8yCIVvODTguiBexO6uM4k");
		textMsg.setText(text);
		textMsg.setMsgtype("text");
		return textMsg;
	}

}
