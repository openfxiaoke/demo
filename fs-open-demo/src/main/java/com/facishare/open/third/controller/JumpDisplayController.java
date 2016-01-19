package com.facishare.open.third.controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.facishare.open.third.manager.impl.ConfigManagerImpl;

/**
 * 页面跳转Controller
 * @author huanghp
 * @date 2015年8月28日
 */

@Controller
public class JumpDisplayController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "configManager")
	private ConfigManagerImpl configManager;
	
	/**
	 * @param session @see HttpSession
	 * @return
	 */
	@RequestMapping("display/bindAccount")
	public ModelAndView displayBind(HttpSession session) {
		ModelAndView mav = new ModelAndView("forward/bindDisplay");
		
		try {
			StringBuffer sb = new StringBuffer("");
			
			Random random = new Random();
			for (int i=0; i<18 ; i++) {
				sb.append(random.nextInt(9));
			}
			
			mav.addObject("appId", configManager.getAppId());
			mav.addObject("redirectUri", configManager.getRedirectUri());
			mav.addObject("bindUri", configManager.getBindUri());
			
			session.setAttribute("tempState", sb.toString());
		} catch (Exception e) {
			mav.addObject("message", "获取openUserId异常");
			logger.error("接收跳转异常",e);
		}
		
		return mav;
	}
	
	/**
	 * @param session @see HttpSession
	 * @return
	 */
	@RequestMapping("display/addressBook")
	public ModelAndView displayaddressBook(HttpSession session) {
		ModelAndView mav = new ModelAndView("forward/addressBook");
		
		mav.addObject("permanentCode", configManager.getPermanentCode());
		
		return mav;
	}
	
}
