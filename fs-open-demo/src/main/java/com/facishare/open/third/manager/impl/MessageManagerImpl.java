package com.facishare.open.third.manager.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.facishare.open.third.beans.args.AppTokenArg;
import com.facishare.open.third.beans.args.CorpAccessTokenArg;
import com.facishare.open.third.beans.results.AppTokenResult;
import com.facishare.open.third.beans.results.CorpAccessTokenResult;
import com.facishare.open.third.beans.results.TemplateMsgResult;
import com.facishare.open.third.beans.results.TextMsgResult;
import com.facishare.open.third.manager.MessageManager;
import com.facishare.open.third.utils.OpenAPIUtils;

@Service("messageManager")
public class MessageManagerImpl implements MessageManager{

	@Resource(name = "configManager")
	private ConfigManagerImpl configManager;
	
	public TextMsgResult sendTextMsg(String[] openUserIds, String msgContent) throws Exception {
		TextMsgResult textMsgResult = new TextMsgResult();
		
		AppTokenArg appTokenArg = new AppTokenArg();
		appTokenArg.setAppId(configManager.getAppId());
		appTokenArg.setAppSecret(configManager.getAppSecret());
		
		AppTokenResult appTokenResult = OpenAPIUtils.getAppToken(appTokenArg);
		
		if (appTokenResult == null || appTokenResult.getErrorCode() != 0) {
			textMsgResult.setErrorCode(appTokenResult.getErrorCode());
			textMsgResult.setErrorMessage(appTokenResult.getErrorMessage());
			
			return textMsgResult;
		}
		
		CorpAccessTokenArg corpAccessTokenArg = new CorpAccessTokenArg();
		corpAccessTokenArg.setAppAccessToken(appTokenResult.getAppAccessToken());
		corpAccessTokenArg.setPermanentCode(configManager.getPermanentCode());
		
		CorpAccessTokenResult corpAccessTokenResult = OpenAPIUtils.getCorpToken(corpAccessTokenArg);
		
		if (corpAccessTokenResult == null || corpAccessTokenResult.getErrorCode() != 0) {
			textMsgResult.setErrorCode(corpAccessTokenResult.getErrorCode());
			textMsgResult.setErrorMessage(corpAccessTokenResult.getErrorMessage());
			
			return textMsgResult;
		}
		
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("corpAccessToken", corpAccessTokenResult.getCorpAccessToken());
		msgMap.put("corpId", corpAccessTokenResult.getCorpId());
		msgMap.put("toUser", openUserIds);
		msgMap.put("msgType", "text");
		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("content", msgContent);
		msgMap.put("text", textMap);
		
		textMsgResult = OpenAPIUtils.sendTextMsg(msgMap);
		
		return textMsgResult;
	}
	
	public TemplateMsgResult sendTempleteMsg(String[] openUserIds, String url, String templateId, Map<String, Map<String, String>> datas) throws Exception {
		TemplateMsgResult templateMsgResult = new TemplateMsgResult();
		
		AppTokenArg appTokenArg = new AppTokenArg();
		appTokenArg.setAppId(configManager.getAppId());
		appTokenArg.setAppSecret(configManager.getAppSecret());
		
		AppTokenResult appTokenResult = OpenAPIUtils.getAppToken(appTokenArg);
		
		if (appTokenResult == null || appTokenResult.getErrorCode() != 0) {
			templateMsgResult.setErrorCode(appTokenResult.getErrorCode());
			templateMsgResult.setErrorMessage(appTokenResult.getErrorMessage());
			
			return templateMsgResult;
		}
		
		CorpAccessTokenArg corpAccessTokenArg = new CorpAccessTokenArg();
		corpAccessTokenArg.setAppAccessToken(appTokenResult.getAppAccessToken());
		corpAccessTokenArg.setPermanentCode(configManager.getPermanentCode());
		
		CorpAccessTokenResult corpAccessTokenResult = OpenAPIUtils.getCorpToken(corpAccessTokenArg);
		
		if (corpAccessTokenResult == null || corpAccessTokenResult.getErrorCode() != 0) {
			templateMsgResult.setErrorCode(corpAccessTokenResult.getErrorCode());
			templateMsgResult.setErrorMessage(corpAccessTokenResult.getErrorMessage());
			
			return templateMsgResult;
		}
		
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("corpAccessToken", corpAccessTokenResult.getCorpAccessToken());
		msgMap.put("corpId", corpAccessTokenResult.getCorpId());
		msgMap.put("toUser", openUserIds);
		msgMap.put("topColor", "#ff0000");
		msgMap.put("url", url);
		msgMap.put("msgType", "template");
		msgMap.put("templateId", templateId);
		msgMap.put("data", datas);
		
		templateMsgResult = OpenAPIUtils.sendTemplateMsg(msgMap);
		
		return templateMsgResult;
	}
	
}
