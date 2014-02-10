package com.check.demo.WechatHelper.sdk;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public interface IMessage {

	// message comes from
	/*public enum USERMESSAGE {
		MESSAGE_EVENT("event"),
		MESSAGE_IMAGE("image"),
		MESSAGE_LINK("link"),
		MESSAGE_LOCATION("location"),
		MESSAGE_TEXT("text");
		private String context;
		public String getContext(){
			return this.context;
		}
	    private USERMESSAGE(String context){
	    	this.context = context;
	    }
	} */
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_TEXT = "text";

	// message response to user
	/*public enum PLATFROMMESSAGE {
		MESSAGE_RESULT_MUSIC("music"),
		MESSAGE_RESULT_NEWS("news"),
		MESSAGE_RESULT_TEXT("text"),;
		private String context;
		public String getContext(){
			return this.context;
		}
	    private PLATFROMMESSAGE(String context){
	    	this.context = context;
	    }
	} */
	public static final String MESSAGE_RESULT_MUSIC = "music";
	public static final String MESSAGE_RESULT_NEWS = "news";
	public static final String MESSAGE_RESULT_TEXT = "text";// different from MESSAGE_TEXT
	public static final String MESSAGE_RESULT_IMAGE = "image";
	public static final String MESSAGE_RESULT_VOICE = "voice";
	public static final String MESSAGE_RESULT_VIDEO = "video";
	
	// event types
	public static final String EVENT_CLICK = "CLICK";
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";

	// all the tags may occur in the message content
	public static final String TAG_ARTICLECOUNT = "ArticleCount";
	public static final String TAG_ARTICLES = "Articles";
	public static final String TAG_CONTENT = "Content";
	public static final String TAG_MEDIAID = "media_id";
	public static final String TAG_VIDEOTITLE = "title";
	public static final String TAG_VIDEODESCRIPTION = "description";
	public static final String TAG_CREATETIME = "CreateTime";
	public static final String TAG_DESCRIPTION = "Description";
	public static final String TAG_EVENT = "Event";
	public static final String TAG_EVENTKEY = "EventKey";
	public static final String TAG_FROMUSERNAME = "FromUserName";
	public static final String TAG_FUNCFLAG = "FuncFlag";
	public static final String TAG_HQMUSICURL = "HQMusicUrl";
	public static final String TAG_ITEM = "item";
	public static final String TAG_LABEL = "Precision";
	public static final String TAG_LOCATIONX = "Location_X";
	public static final String TAG_LOCATIONY = "Location_Y";
	public static final String TAG_MSGID = "MsgId";
	public static final String TAG_MSGTYPE = "MsgType";
	public static final String TAG_MUSIC = "Music";
	public static final String TAG_VOICE = "Voice";
	public static final String TAG_IMAGE = "Image";
	public static final String TAG_VIDEO = "Video";
	public static final String TAG_MUSICURL = "MusicUrl";
	public static final String TAG_PICURL = "PicUrl";
	public static final String TAG_SCALE = "Scale";
	public static final String TAG_TITLE = "Title";
	public static final String TAG_TOUSERNAME = "ToUserName";
	public static final String TAG_URL = "Url";
	public static final String TAG_XML = "xml";

}
