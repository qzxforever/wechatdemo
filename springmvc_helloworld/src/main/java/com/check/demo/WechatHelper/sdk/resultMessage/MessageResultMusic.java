package com.check.demo.WechatHelper.sdk.resultMessage;

import com.check.demo.WechatHelper.sdk.ItemMusic;
import com.check.demo.WechatHelper.sdk.Message;

/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class MessageResultMusic extends Message {

	protected ItemMusic music;
	protected int funcFlag;

	public int getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}

	public ItemMusic getMusic() {
		return music;
	}

	public void setMusic(ItemMusic music) {
		this.music = music;
	}

}
