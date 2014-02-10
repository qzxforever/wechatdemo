package com.check.demo.WechatHelper;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.check.demo.WechatHelper.msgFactory.MsgFactory;
import com.check.demo.WechatHelper.msgFactory.MsgHandlerFactory;
import com.check.demo.WechatHelper.msgFactory.MsgResultFactory;
import com.check.demo.WechatHelper.sdk.IMessage;
import com.check.demo.WechatHelper.sdk.Message;
import com.check.demo.WechatHelper.sdk.message.MessageHandlerHelper;
import com.check.demo.WechatHelper.sdk.resultMessage.MessageResultHandlerHelper;

public class WechatProcessor {
	private HttpServletRequest request;// request
	private Message message;// message comes from
	private HttpServletResponse response;// response
	private Message messageResult;// message will return
	private MessageHandlerHelper messageHadler;// handle message
	private MessageResultHandlerHelper messageResultHandler;// handle result message
	private static final String TOKEN = "WechatMarketing";
	
	public void dealMessage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		this.request = request;
		this.response = response;
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp"); 
		if (signature != null && checkSignature(signature, timestamp, nonce)) {// message is from weixin server
			if (echostr != null) {// when get! when post, echostr is null or if(echostr != null)
				response.setContentType("text/plain");
				response.getWriter().write(echostr);
			} else {// do post 
				response.setContentType("text/xml;charset=UTF-8");// important! otherwise error text encoding
				
				try {
					parseInputStreamToMessage();// parse message occurs a exception
				} catch (Exception e) {
					e.printStackTrace();
				} 
				messageResult = messageHadler.handleMessage(message);
				writeMessageToOuputStream();
				
			} 
		} else {// not from weixin
			try {
				request.getRequestDispatcher("/views/test.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}

	// write the result message
		private void writeMessageToOuputStream() throws IOException {
			messageResultHandler = new MsgResultFactory(messageResult.getMsgType()).create(); 
			String resultContent = messageResultHandler.buildMessageResult(messageResult);
			response.getWriter().print(resultContent);
		}

		private void parseInputStreamToMessage() throws Exception {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			Document document = factory.newDocumentBuilder().parse(request.getInputStream());
			Element root = document.getDocumentElement(); 
			String type = (root.getElementsByTagName(IMessage.TAG_MSGTYPE)).item(0).getTextContent();// filter CDATA... -- text/image/...		

			message = new MsgFactory(type).create();
			messageHadler = new MsgHandlerFactory(type).create();
			messageHadler.parseMessage(message, root);// do the default/common parse!
		}

		// check signature
		// signature=29ba6d9ea244e799091f8525ca8d1ee480f2a583&echostr=5877301187008054751&timestamp=1368966622&nonce=1368415815
		//signature=29ba6d9ea244e799091f8525ca8d1ee480f2a583&timestamp=1368966622&nonce=1368415815
		public boolean checkSignature(String signature, String timestamp, String nonce) {
			// dictionary sort
			String[] dataStrings = new String[] { TOKEN, timestamp, nonce };
			Arrays.sort(dataStrings);
			// construct the three string
			String data = dataStrings[0] + dataStrings[1] + dataStrings[2];
			// sha1
			@SuppressWarnings("deprecation")
			String flag = DigestUtils.shaHex(data);
			// check 
			if (flag.equalsIgnoreCase(signature)) { 
				return true;
			} 
			else { 
				return false;
			}
		}
	
}
