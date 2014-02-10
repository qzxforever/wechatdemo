package com.check.demo.WechatHelper.filters;

import java.util.ArrayList;
import java.util.List;

import com.check.demo.WechatHelper.sdk.Message;



/**
 * @author check
 * 2013-12-26
 * email:qzxandgj@aliyun.com
 */
public class FilterChain {

	public List<IMessageFilter> filters = new ArrayList<IMessageFilter>();

	// do filter chain
	public Message doFilterChain(Message message) {
		Message messageResult = null;
		for (int i = 0; i < filters.size(); i++) {
			messageResult = filters.get(i).doMessageFilter(message);
			if (messageResult != null) {
				return messageResult;// if one filter can deal the message,then do it!so,it can be returned!
			}
		}
		return messageResult;// if none of the filter can do it!
	}

	// add a filter
	public void addFilter(IMessageFilter filter) {
		filters.add(filter);
	}

	public List<IMessageFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<IMessageFilter> filters) {
		this.filters = filters;
	}

}
