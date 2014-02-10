package com.check.demo.WeChatUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.check.demo.WeChatUtil.pojo.AccessToken;
import com.check.demo.WeChatUtil.pojo.menu.Menu;
import com.check.demo.WeChatUtil.pojo.serviceMessage.Msg;
import com.check.demo.WeChatUtil.pojo.weuser.WeUser;

/**
 * ����ƽ̨ͨ�ýӿڹ�����
 * 
 * @author check
 * @date 2013-12-27
 */
public class WeixinUtil {

	/**
	 * ����https���󲢻�ȡ���
	 * 
	 * @param requestUrl �����ַ
	 * @param requestMethod ����ʽ��GET��POST��
	 * @param outputStr �ύ�����
	 * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		//���ô�������������ڱ��ز���
		/*System.setProperty("https.proxySet", "true");
		System.setProperty("https.proxyHost", "isasrv");
		System.setProperty("https.proxyPort","80");*/
		
		try {
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// ���������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ�
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("Weixin server connection timed out.");
		} catch (Exception e) {
			System.out.println("https request error:{}"+e);
		}
		return jsonObject;
	}
	
	// ��ȡaccess_token�Ľӿڵ�ַ��GET�� ��200����/�죩
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * ��ȡaccess_token
	 * 
	 * @param appid ƾ֤
	 * @param appsecret ��Կ
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// ��ȡtokenʧ��
				System.out.println("��ȡtokenʧ�� errcode:{} errmsg:{}"+ jsonObject.getIntValue("errcode")+ jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	// �˵�������POST�� ��100����/�죩
	public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * �����˵�
	 * 
	 * @param menu �˵�ʵ��
	 * @param accessToken ��Ч��access_token
	 * @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// ƴװ�����˵���url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// ���˵�����ת����json�ַ�
		String jsonMenu = JSONObject.toJSONString(menu).toString();
		// ���ýӿڴ����˵�
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getIntValue("errcode")) {
				result = jsonObject.getIntValue("errcode");
				System.out.println("�����˵�ʧ�� errcode:{} errmsg:{}"+ jsonObject.getIntValue("errcode")+ jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	// ɾ���POST�� ��100����/�죩
	public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * ɾ��˵�
	 * 
	 * @param accessToken ��Ч��access_token
	 * @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	 */
	public static int deleteMenu(String accessToken){
		int result = 0;

		// ƴװ�����˵���url
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		// ���ýӿڴ����˵�
		JSONObject jsonObject = httpRequest(url, "POST", null);

		if (null != jsonObject) {
			if (0 != jsonObject.getIntValue("errcode")) {
				result = jsonObject.getIntValue("errcode");
				System.out.println("ɾ��˵�ʧ�� errcode:{} errmsg:{}"+ jsonObject.getIntValue("errcode")+ jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	
	// �ظ���Ϣ��24Сʱ֮�ڵĻ���
	public final static String menu_send_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	/**
	 * �ظ���Ϣ
	 * 
	 * @param msg ��Ϣʵ��
	 * @param accessToken ��Ч��access_token
	 * @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	 */
	public static int send(Msg msg, String accessToken){
		int result = 0;
		// ƴװ�����˵���url
		String url = menu_send_url.replace("ACCESS_TOKEN", accessToken);
		// ����Ϣ����ת����json
		String jsonMsg = JSONObject.toJSONString(msg).toString();
		System.out.println(jsonMsg);
    	// ���ýӿڴ����˵�
		JSONObject jsonObject = httpRequest(url, "POST", jsonMsg);
		System.out.println(jsonObject);
		if (null != jsonObject) {
			if (0 != jsonObject.getIntValue("errcode")) {
				result = jsonObject.getIntValue("errcode");
				System.out.println("ɾ��˵�ʧ�� errcode:{} errmsg:{}"+ jsonObject.getIntValue("errcode")+ jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	// ��ȡ��ע�û��б�
		public final static String menu_getUserList_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

	/**
	 * ��ȡ��ע�û��б�
	 * 
	 * @param accessToken ��Ч��access_token
	 * @param next_openid ��һ����ȡid
	 * @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	 */
	public static List<String> getUserList(String accessToken, String next_openid){
		next_openid = null==next_openid?"":next_openid;
		String jsonUserList = null;
		String requestUrl = menu_getUserList_url.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", next_openid);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			try {
				jsonUserList = jsonObject.getString("data");
			} catch (JSONException e) {
				accessToken = null;
				// ��ȡtokenʧ��
				System.out.println("��ȡ�û��б�ʧ�� errcode:{} errmsg:{}"+ jsonObject.getIntValue("errcode")+ jsonObject.getString("errmsg"));
			}
		}
		System.out.println(jsonUserList);
		jsonUserList = JSONObject.parseObject(jsonUserList).getString("openid");
		System.out.println(jsonUserList);
		JSONArray arr = JSONArray.parseArray(jsonUserList);
		
		List<String> ids = new LinkedList<String>();
		for(int i = 0; i < arr.size(); i++){
			ids.add(arr.getString(i));
		}
		return ids;
	}
	
	// ��ȡ��ע�û��б���Ϣ
	public final static String menu_getUserInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * ��ȡ��ע�û��б���Ϣ
	 * 
	 * @param accessToken ��Ч��access_token
	 * @param openid �û���open_id
	 * @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	 */
	public static WeUser getUserInfo(String accessToken, String openid){
		WeUser user = new WeUser();
		String requestUrl = menu_getUserInfo_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			try {
				user.setCity(jsonObject.getString("city"));
				user.setCountry(jsonObject.getString("country"));
				user.setHeadimgurl(jsonObject.getString("headimgurl"));
				user.setLanguage(jsonObject.getString("language"));
				user.setNickName(jsonObject.getString("nickname"));
				user.setOpen_id(jsonObject.getString("openid"));
				user.setProvince(jsonObject.getString("province"));
				user.setSex(jsonObject.getString("sex"));
				user.setSubscribe(jsonObject.getString("subscribe"));
				user.setSubscribe_time(jsonObject.getString("subscribe_time"));
			} catch (JSONException e) {
				accessToken = null;
				// ��ȡtokenʧ��
				System.out.println("��ȡ�û��б�ʧ�� errcode:{} errmsg:{}"+ jsonObject.getIntValue("errcode")+ jsonObject.getString("errmsg"));
			}
		}	
		//���ϲ��ֿ��Ÿ߼��ӿں�ʹ�ã����Խ׶Σ������
		return user;
	}
}