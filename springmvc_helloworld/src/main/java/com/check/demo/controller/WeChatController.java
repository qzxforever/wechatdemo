package com.check.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.check.demo.WechatHelper.WechatProcessor;

@Controller
public class WeChatController {
	WechatProcessor wechatProcessor = new WechatProcessor();
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void weixinGet(Model model, HttpServletRequest request,HttpServletResponse response) {
		try {
			wechatProcessor.dealMessage(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void helloworld(Model model, HttpServletRequest request,HttpServletResponse response) {
		try {
			wechatProcessor.dealMessage(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
