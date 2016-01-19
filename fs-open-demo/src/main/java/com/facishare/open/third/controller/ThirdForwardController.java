package com.facishare.open.third.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.facishare.open.third.beans.AppReqParmVO;
import com.facishare.open.third.beans.MsgReceiveParamVO;
import com.facishare.open.third.beans.RecMessageBody;
import com.facishare.open.third.beans.ResponseResultVO;
import com.facishare.open.third.beans.args.AppTokenArg;
import com.facishare.open.third.beans.args.BindAccountArg;
import com.facishare.open.third.beans.args.CorpAccessTokenArg;
import com.facishare.open.third.beans.args.DeptListArg;
import com.facishare.open.third.beans.args.OpenUserIdArg;
import com.facishare.open.third.beans.args.UserInfoArg;
import com.facishare.open.third.beans.emun.RecMessageType;
import com.facishare.open.third.beans.results.AppTokenResult;
import com.facishare.open.third.beans.results.BindAccountResult;
import com.facishare.open.third.beans.results.CorpAccessTokenResult;
import com.facishare.open.third.beans.results.CorpUserMapResult;
import com.facishare.open.third.beans.results.DeptListResult;
import com.facishare.open.third.beans.results.OpenUserIdResult;
import com.facishare.open.third.beans.results.TemplateMsgResult;
import com.facishare.open.third.beans.results.TextMsgResult;
import com.facishare.open.third.beans.results.UserInfoResult;
import com.facishare.open.third.manager.AddressBookManager;
import com.facishare.open.third.manager.MessageManager;
import com.facishare.open.third.manager.impl.ConfigManagerImpl;
import com.facishare.open.third.utils.OpenAPIUtils;
import com.facishare.open.third.utils.SigUtils;
import com.google.gson.Gson;

/**
 * 接收外部请求 Controller
 * @author huanghp
 * @date 2015年8月28日
 */

@Controller
public class ThirdForwardController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//初始化google对json解析的对象
	private Gson gson = new Gson();
	
	@Resource(name = "configManager")
	private ConfigManagerImpl configManager;

	@Resource(name = "addressBookManager")
	private AddressBookManager addressBookManager;

	@Resource(name = "messageManager")
	private MessageManager messageManager;

	/**
	 * 开平APP跳转或者Web跳转调用(回调接口)
	 * 接收App跳转的入口方法
	 * @param reqCodeForm @see ReqCodeFormVO
	 * @param session @see HttpSession
	 * @return
	 */
	@RequestMapping("app/forward")
	public ModelAndView handleAppForward(AppReqParmVO appReqParmVO, HttpSession session) {
		ModelAndView mav = new ModelAndView("forward/forwardResult");
		
		try {
			//验证第三方跳转传入参数是否完整
			if (appReqParmVO == null || StringUtils.isEmpty(appReqParmVO.getNonce()) || StringUtils.isEmpty(appReqParmVO.getCode())
					|| StringUtils.isEmpty(appReqParmVO.getCodeSig()) || StringUtils.isEmpty(appReqParmVO.getTimestamp())) {
				mav.addObject("message", "请求参数不完整");
				return mav;
			}
			
			//验证时间戳格式
			if (!StringUtils.isNumeric(appReqParmVO.getTimestamp())) {
				mav.addObject("message", "时间戳格式不对");
				return mav;
			}
			
			long nowMills = System.currentTimeMillis();
			Long timeMills = Long.parseLong(appReqParmVO.getTimestamp());
			
			//当前时间上下50s 都认为是时间误差接受该请求
			if (Math.abs(nowMills - timeMills) > 500000) {
				mav.addObject("message", "请求timestamp已经过期");
				return mav;
			}
			
			//请求参数+token 验证请求是否合法
			boolean verifyResult = OpenAPIUtils.verifyAppReq(appReqParmVO, configManager.getToken());
			if (!verifyResult) {
				mav.addObject("message", "请求参数验证不通过");
				return mav;
			}
			
			//通过appId 和 appSecret 获取appToken [实际开发中应该获取就缓存不应该每次去调用接口获取]
			AppTokenArg appTokenArg = new AppTokenArg();
			appTokenArg.setAppId(configManager.getAppId());
			appTokenArg.setAppSecret(configManager.getAppSecret());
			
			AppTokenResult appTokenResult = OpenAPIUtils.getAppToken(appTokenArg);
			//errorCode == 0 表示获取成功
			if (appTokenResult == null || appTokenResult.getErrorCode() != 0) {
				mav.addObject("message", "获取appToken错误：errorCode=" + appTokenResult.getErrorCode() 
						+ "errmsg=" + appTokenResult.getErrorMessage());
				return mav;
			}
			
			//通过appToken 和 code 获取跳转人员信息
			OpenUserIdArg openUserIdArg = new OpenUserIdArg();
			openUserIdArg.setAppAccessToken(appTokenResult.getAppAccessToken());
			openUserIdArg.setCode(appReqParmVO.getCode());
			
			OpenUserIdResult openUserIdResult = OpenAPIUtils.getOpenUserId(openUserIdArg);
			
			//errorCode == 0 表示获取成功
			if (openUserIdResult != null && openUserIdResult.getErrorCode() == 0) {
				mav.addObject("openUser", openUserIdResult);
				mav.addObject("message", "获取openUserId成功 ");
				mav.addObject("findstatus", "0");
			} else {
				mav.addObject("message", "获取openUserId失败 errcode=" + openUserIdResult.getErrorCode() 
						+ "errmsg=" + openUserIdResult.getErrorCode());
			}
		} catch (Exception e) {
			mav.addObject("message", "获取openUserId异常");
			logger.error("接收跳转异常",e);
		}
		
		return mav;
	}

	/**
	 * 调用开平CGI接口获取成员详细信息
	 * 获取跳转过来的人员信息入口方法
	 * @param openUserId
	 * @return
	 */
	@RequestMapping("user/getInfo")
	@ResponseBody
	public ResponseResultVO getUserInfo(String openUserId) {
		ResponseResultVO responseResult = new ResponseResultVO();
		
		try {
			if(StringUtils.isBlank(openUserId)) {
				responseResult.setRs("{\"errLabel\":\"openUserId参数不存在\"}");
				return responseResult;
			}
			
			AppTokenArg appTokenArg = new AppTokenArg();
			appTokenArg.setAppId(configManager.getAppId());
			appTokenArg.setAppSecret(configManager.getAppSecret());
			
			AppTokenResult appTokenResult = OpenAPIUtils.getAppToken(appTokenArg);
			if (appTokenResult == null || appTokenResult.getErrorCode() != 0) {
				responseResult.setRs("{\"errLabel\":\"获取appTokenResult异常\",\"errmsg\":\""
									+ appTokenResult.getErrorMessage() +"\",\"errorCode\":\""
									+ appTokenResult.getErrorCode() + "\"}");
				return responseResult;
			}
			
			CorpAccessTokenArg corpAccessTokenArg = new CorpAccessTokenArg();
			corpAccessTokenArg.setAppAccessToken(appTokenResult.getAppAccessToken());
			corpAccessTokenArg.setPermanentCode(configManager.getPermanentCode());
			
			CorpAccessTokenResult corpAccessTokenResult = OpenAPIUtils.getCorpToken(corpAccessTokenArg);
			if (corpAccessTokenResult == null || corpAccessTokenResult.getErrorCode() != 0) {
				responseResult.setRs("{\"errLabel\":\"获取corpAccessToken异常\",\"errmsg\":\""
									+ corpAccessTokenResult.getErrorMessage() +"\",\"errorCode\":\""
									+ corpAccessTokenResult.getErrorCode() + "\"}");
				return responseResult;
			}
			
			UserInfoArg userInfoArg = new UserInfoArg();
			userInfoArg.setCorpId(corpAccessTokenResult.getCorpId());
			userInfoArg.setCorpAccessToken(corpAccessTokenResult.getCorpAccessToken());
			userInfoArg.setOpenUserId(openUserId);
			
			UserInfoResult userInfoResult = OpenAPIUtils.getUserInfo(userInfoArg);
			
			if (userInfoResult == null || userInfoResult.getErrorCode() != 0) {
				responseResult.setRs("{\"errLabel\":\"获取用户信息异常\",\"errmsg\":\""
									+ userInfoResult.getErrorMessage() +"\",\"errorCode\":\""
									+ userInfoResult.getErrorCode() + "\"}");
				return responseResult;
			}
			
			responseResult.setCode("200");
			responseResult.setRs(gson.toJson(userInfoResult));
		} catch (Exception e) {
			logger.error("获取用户信息异常",e);
			responseResult.setRs("{\"errLabel\":\"获取用户信息异常\"}");
		}
		
		return responseResult;
	}
	
	
	/**
	 * 开平重定向进行调用(回调接口)
	 * 浏览器跳转到登陆/注册页面处理完后的回调入口
	 * @param code 开放平台传过来的用户身份临时票据
	 * @param state 第三方跳转到开放平台所生成的随机数用于验证请求
	 * @param session @see HttpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("user/callback")
	public ModelAndView processCallBack(String code,String state,HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("forward/callbackResult");
		
		try {
			if (StringUtils.isBlank(code) || StringUtils.isBlank(state)) {
				mav.addObject("message", "请求参数不完整");
				return mav;
			}
			
			String sessionStat = (String)session.getAttribute("tempState");
			
			session.removeAttribute("tempState");
			
			//Demo简单用一次性session 中state对应来验证来源，实际中看情况采取验证策略
			if(!state.equals(sessionStat)) {
				mav.addObject("message", "请求来源state不符合");
				return mav;
			}
			
			AppTokenArg appTokenArg = new AppTokenArg();
			appTokenArg.setAppId(configManager.getAppId());
			appTokenArg.setAppSecret(configManager.getAppSecret());	
			AppTokenResult appTokenResult = OpenAPIUtils.getAppToken(appTokenArg);
			
			if (appTokenResult == null || appTokenResult.getErrorCode() != 0) {
				mav.addObject("message", "获取appToken错误：errcode="
							+ appTokenResult.getErrorCode() + "errmsg="
							+ appTokenResult.getErrorMessage());
				return mav;
			}
			
			//通过appToken 和 code 获取跳转人员信息
			OpenUserIdArg openUserIdArg = new OpenUserIdArg();
			openUserIdArg.setAppAccessToken(appTokenResult.getAppAccessToken());
			openUserIdArg.setCode(code);
			
			OpenUserIdResult openUserIdResult = OpenAPIUtils.getOpenUserId(openUserIdArg);
			
			if (openUserIdResult == null || openUserIdResult.getErrorCode() != 0) {
				mav.addObject("message", "获取openUserId错误：errcode="
							+ openUserIdResult.getErrorCode() + "errmsg="
							+ openUserIdResult.getErrorMessage());
				return mav;
			}
			
			BindAccountArg bindAccountArg = new BindAccountArg();
			bindAccountArg.setOpenUserId(openUserIdResult.getOpenUserId());
			bindAccountArg.setAppAccessToken(appTokenResult.getAppAccessToken());
			
			//Demo 中由于没有实际的用户所以简单模拟，实际业务系统中要用真实账户做绑定建对应关系
			bindAccountArg.setAppAccount("app" + state);
			
			BindAccountResult bindAccountResult = OpenAPIUtils.bindAccount(bindAccountArg);
			if (bindAccountResult == null || bindAccountResult.getErrorCode() != 0) {
				mav.addObject("message", "绑定用户错误：errcode="
							+ bindAccountResult.getErrorCode() + "errmsg="
							+ bindAccountResult.getErrorMessage());
				return mav;
			}
			
			mav.addObject("bindUser", bindAccountResult);
			mav.addObject("message", "绑定成功 ");
			mav.addObject("findstatus", "0");
		} catch(Exception e) {
			mav.addObject("message", "处理登陆注册回调异常");
			logger.error("处理登陆注册回调异常",e);
		}
		
		return mav;
	}
	
	/**
	 * 调用开平CGI接口获取部门列表
	 * 获取企业所有部门
	 * @param permanentCode 永久授权码
	 * @return @see ResponseResultVO
	 */
	@ResponseBody
	@RequestMapping("dept/list")
	public ResponseResultVO getDeptList(String permanentCode) {
		ResponseResultVO responseResult = new ResponseResultVO();
		
		try {
			if (StringUtils.isBlank(permanentCode)) {
				responseResult.setRs("{\"errLabel\":\"permanentCode参数不存在\"}");
				return responseResult;
			}
			
			AppTokenArg appTokenArg = new AppTokenArg();
			appTokenArg.setAppId(configManager.getAppId());
			appTokenArg.setAppSecret(configManager.getAppSecret());
			
			AppTokenResult appTokenResult = OpenAPIUtils.getAppToken(appTokenArg);
			if (appTokenResult == null || appTokenResult.getErrorCode() != 0) {
				responseResult.setRs("{\"errLabel\":\"获取appTokenResult异常\",\"errmsg\":\""
								+ appTokenResult.getErrorMessage() + "\",\"errorCode\":\""
								+ appTokenResult.getErrorCode() + "\"}");
				return responseResult;
			}
			
			CorpAccessTokenArg corpAccessTokenArg = new CorpAccessTokenArg();
			corpAccessTokenArg.setAppAccessToken(appTokenResult.getAppAccessToken());
			corpAccessTokenArg.setPermanentCode(configManager.getPermanentCode());
			
			CorpAccessTokenResult corpAccessTokenResult = OpenAPIUtils.getCorpToken(corpAccessTokenArg);
			if (corpAccessTokenResult == null || corpAccessTokenResult.getErrorCode() != 0) {
				responseResult.setRs("{\"errLabel\":\"获取corpAccessToken异常\",\"errmsg\":\""
									+corpAccessTokenResult.getErrorMessage() +"\",\"errorCode\":\""
									+ corpAccessTokenResult.getErrorCode() + "\"}");
				return responseResult;
			}
			
			DeptListArg deptListArg = new DeptListArg();
			deptListArg.setCorpId(corpAccessTokenResult.getCorpId());
			deptListArg.setCorpAccessToken(corpAccessTokenResult.getCorpAccessToken());
			
			long starttime = System.currentTimeMillis();
			
			DeptListResult deptListResult = OpenAPIUtils.getDeptList(deptListArg);
			
			long end = System.currentTimeMillis();
			logger.debug("获取全公司人员耗时 ：" + (end - starttime) + "毫秒");
			
			if (deptListResult == null || deptListResult.getErrorCode() != 0) {
				responseResult.setRs("{\"errLabel\":\"获取用户信息异常\",\"errmsg\":\""
									+ deptListResult.getErrorMessage() +"\",\"errorCode\":\""
									+ deptListResult.getErrorCode() + "\"}");
				return responseResult;
			}
			
			responseResult.setCode("200");
			responseResult.setTime((end - starttime) + "毫秒");
			responseResult.setRs(gson.toJson(deptListResult));
		} catch (Exception e) {
			logger.error("获取用户信息异常",e);
			responseResult.setRs("{\"errLabel\":\"获取用户信息异常\"}");
		}
		
		return responseResult;
	}

	/**
	 * 调用开平CGI接口获取用户列表
	 * 获取企业所有人员信息入口方法
	 * @param permanentCode 企业永久授权码
	 * @return @see ResponseResultVO
	 */
	@ResponseBody
	@RequestMapping("user/corpUserMap")
	public ResponseResultVO getCorpUserMap(String permanentCode) {
		ResponseResultVO responseResult = new ResponseResultVO();
		
		try {
			if (StringUtils.isBlank(permanentCode)) {
				responseResult.setRs("{\"errLabel\":\"permanentCode参数不存在\"}");
				return responseResult;
			}
			
			long starttime = System.currentTimeMillis();
			
			CorpUserMapResult corpUserMapResult = addressBookManager.getCorpEmployeeMap(permanentCode);
			
			long end = System.currentTimeMillis();
			logger.debug("获取全公司人员耗时 ：" + (end - starttime) + "毫秒");
			
			if (corpUserMapResult == null || corpUserMapResult.getErrorCode() != 0) {
				responseResult.setRs("{\"errLabel\":\"获取全公司人员信息异常\",\"errmsg\":\""
								+ corpUserMapResult.getErrorMessage() +"\",\"errorCode\":\""
								+ corpUserMapResult.getErrorCode() + "\"}");
				return responseResult;
			}
			
			responseResult.setCode("200");
			responseResult.setTime((end - starttime) + "毫秒");
			responseResult.setRs(gson.toJson(corpUserMapResult));
			
		} catch(Exception e) {
			logger.error("获取公司人员信息异常",e);
			responseResult.setRs("{\"errLabel\":\"获取公司人员信息异常\"}");
		}
		
		return responseResult;
	}
	
	/**
	 * 调用开平CGI接口批量获取用户列表
	 * 批量获取企业所有人员信息入口方法
	 * @param permanentCode 企业永久授权码
	 * @return @see ResponseResultVO
	 */
	@ResponseBody
	@RequestMapping("user/batchCorpUserMap")
	public ResponseResultVO getCorpUserMapByBatch(String permanentCode) {
		ResponseResultVO responseResult = new ResponseResultVO();
		
		try {
			if (StringUtils.isBlank(permanentCode)) {
				responseResult.setRs("{\"errLabel\":\"permanentCode参数不存在\"}");
				return responseResult;
			}
			
			long starttime = System.currentTimeMillis();
			
			CorpUserMapResult corpUserMapResult = addressBookManager.getCorpEmployeeMapByBatch(permanentCode);
			
			long end = System.currentTimeMillis();
			logger.debug("批量获取全公司人员耗时 ：" + (end - starttime) + "毫秒");
			
			if (corpUserMapResult == null || corpUserMapResult.getErrorCode() != 0) {
				responseResult.setRs("{\"errLabel\":\"批量获取全公司人员信息异常\",\"errmsg\":\""
								+ corpUserMapResult.getErrorMessage() +"\",\"errorCode\":\""
								+ corpUserMapResult.getErrorCode() + "\"}");
				return responseResult;
			}
			
			responseResult.setCode("200");
			responseResult.setTime((end - starttime) + "毫秒");
			responseResult.setRs(gson.toJson(corpUserMapResult));
			
		} catch(Exception e) {
			logger.error("批量获取公司人员信息异常",e);
			responseResult.setRs("{\"errLabel\":\"批量获取公司人员信息异常\"}");
		}
		
		return responseResult;
	}

	/**
	 * 调用开平CGI接口给用户发送文本消息
	 * @param msgContent 文本消息内容
	 * @param openUserIds 人员Id 用英文,隔开
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("message/send")
	@ResponseBody
	public ResponseResultVO sendTetxMessage(String msgContent, String openUserIds) throws Exception {
		ResponseResultVO responseResult = new ResponseResultVO();
		
		try {
			if (StringUtils.isBlank(openUserIds) || StringUtils.isBlank(msgContent)) {
				responseResult.setRs("{\"errLabel\":\"参数不完整\"}");
				return responseResult;
			}
			
			TextMsgResult textMsgResult = messageManager.sendTextMsg(new String[] {openUserIds}, msgContent);
			
			if(textMsgResult != null) {
				 responseResult.setCode("200");
				 responseResult.setRs(gson.toJson(textMsgResult));
			}	
		} catch(Exception e) {
			logger.error("发送文本消息异常",e);
			responseResult.setRs("{\"errLabel\":\"发送文本消息异常\"}");
		}
		
		return responseResult;
	}
	
	/**
	 * 调用开平CGI接口给用户发送模板消息
	 * @param openUserIds 人员Id 用英文,隔开
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("message/sendTemplate")
	@ResponseBody
	public ResponseResultVO sendTemplateMessage(String openUserIds) throws Exception {
		ResponseResultVO responseResult = new ResponseResultVO();
		
		try {
			if (StringUtils.isBlank(openUserIds)) {
				responseResult.setRs("{\"errLabel\":\"参数不完整\"}");
				return responseResult;
			}
			
			//业务中根据实际地址来
			String url = "http://www.baidu.com";
			//业务中根据实际地址来弄
			String templateId = "201509091441802180519";
			
			Map<String, Map<String, String>> datas = new HashMap<String, Map<String, String>>();
			
			Map<String, String> firstMap = new HashMap<String, String>();
			firstMap.put("value", "恭喜你购买成功！");
			firstMap.put("color", "#173177");
			datas.put("first", firstMap);
			
			Map<String, String> keyword1Map = new HashMap<String, String>();
			keyword1Map.put("value", "巧克力 ");
			keyword1Map.put("color", "#173177");
			datas.put("keyword1", keyword1Map);
			
			Map<String, String> keyword2Map = new HashMap<String, String>();
			keyword2Map.put("value", "39.8元");
			keyword2Map.put("color", "#173177");
			datas.put("keyword2", keyword2Map);
			
			Map<String, String> keyword3Map = new HashMap<String, String>();
			keyword3Map.put("value", "2014年9月16日 ");
			keyword3Map.put("color", "#173177");
			datas.put("keyword3", keyword3Map);
			
			Map<String, String> remarkMap = new HashMap<String, String>();
			remarkMap.put("value", "欢迎再次购买！ ");
			remarkMap.put("color", "#173177");
			datas.put("remark", remarkMap);
			
			TemplateMsgResult templateMsgResult = messageManager.sendTempleteMsg(new String[] {openUserIds}, url, templateId, datas);
			
			if(templateMsgResult != null) {
				 responseResult.setCode("200");
				 responseResult.setRs(gson.toJson(templateMsgResult));
			}
		} catch(Exception e) {
			logger.error("发送模板消息异常",e);
			responseResult.setRs("{\"errLabel\":\"发送模板消息异常\"}");
		}
		
		return responseResult;
	}

	/**
	 * 接收开平推送过来的消息的方法
	 * @param msgReceiveParamVO @see MsgReceiveParamVO
	 * @return "success" 表示接收成功,其它表示失败
	 * 方法需保证在3秒内返回，否则开平认为推送失败会重试一次
	 * 如果存在复杂的逻辑处理不能保证3秒内返回，需考虑先返回然后开线程去处理业务
	 */
	@ResponseBody
	@RequestMapping(value = "parse/receiveMsg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String parseReceiveMsg(@RequestBody MsgReceiveParamVO msgReceiveParamVO) {
		//验证推送传入参数是否完整
		if (msgReceiveParamVO == null || StringUtils.isEmpty(msgReceiveParamVO.getNonce())
				|| StringUtils.isEmpty(msgReceiveParamVO.getContent())
				|| StringUtils.isEmpty(msgReceiveParamVO.getSig())
				|| StringUtils.isEmpty(msgReceiveParamVO.getTimeStamp())) { 
			logger.error("请求参数不完整");
			return "failure";
		}
		
		//验证时间戳格式
		if (!StringUtils.isNumeric(msgReceiveParamVO.getTimeStamp())) {
			logger.error("时间戳格式不对");
			return "failure";
		}
		
		long nowMills = System.currentTimeMillis();
		Long timeMills = Long.parseLong(msgReceiveParamVO.getTimeStamp());
		
		//当前时间上下50s 都认为是时间误差接受该请求
		if (Math.abs(nowMills - timeMills) > 500000) {
			logger.error("请求timestamp已经过期");
			return "failure";
		}
		
		//传人参数+token 进行参数合法性验证
		boolean validateResult = OpenAPIUtils.verifyMsgReq(msgReceiveParamVO, configManager.getToken());
		if (!validateResult) {
			logger.error("请求参数签名校验不通过");
			return "failure";
		}
		
		//通过AESKey 对解密授权码
		String messageContent = SigUtils.decodeAES(msgReceiveParamVO.getContent(), configManager.getEncodingAesKey());
		
		//具体请参照开平API文档 <接收消息与事件>章节
		
		if (StringUtils.isNotEmpty(messageContent)) {
			logger.info("获取的messageConteng={}", messageContent);
			
			RecMessageBody recMessageBody = gson.fromJson(messageContent, RecMessageBody.class);
			
			if (recMessageBody == null) {
				logger.error("解析内容出错");
				return "failure";
			}
			
			if (!StringUtils.equals(recMessageBody.getType(), RecMessageType.CODE.getTypeName())
					&& !StringUtils.equals(recMessageBody.getType(), RecMessageType.CANCLEPERMANENTCODE.getTypeName())
					&& !StringUtils.equals(recMessageBody.getType(), RecMessageType.PERMANENTCODE.getTypeName())
					&& !StringUtils.equals(recMessageBody.getType(), RecMessageType.MESSAGE.getTypeName())) {
				logger.error("消息类型不合法");
				return "failure";
			}
			
			//判断消息为Code 消息
			if (StringUtils.equals(recMessageBody.getType(), RecMessageType.CODE.getTypeName())) {
				//Demo 只做logger 打印 实际中要做接收到Code后的业务逻辑处理
				logger.info("消息为Code 消息,Code={}", recMessageBody.getContent());
			}
			
			//判断消息为PermanentCode 消息
			if (StringUtils.equals(recMessageBody.getType(), RecMessageType.PERMANENTCODE.getTypeName())) {
				//Demo 只做logger 打印 实际中要做接收到PermanentCode后的业务逻辑处理
				logger.info("消息为PermanentCode 消息,PermanentCode={}", recMessageBody.getContent());
			}
			
			//判断消息为CanclePermanentCode 消息
			if (StringUtils.equals(recMessageBody.getType(), RecMessageType.CANCLEPERMANENTCODE.getTypeName())) {
				//Demo 只做logger 打印 实际中要做接收到CanclePermanentCode后的业务逻辑处理
				logger.info("消息为CanclePermanentCode 消息,PermanentCode={}", recMessageBody.getContent());
			}
			
			//判断消息为Message(包括文本和事件) 消息
			if (StringUtils.equals(recMessageBody.getType(), RecMessageType.MESSAGE.getTypeName())) {
				
				Map<String,String> contentMap = gson.fromJson(recMessageBody.getContent(), Map.class);
				
				if ("text".equals(contentMap.get("MsgType"))) {
					//Demo 只做logger 打印 实际中要做接收到Text Message后的业务逻辑处理
					logger.info("消息为Text Message 消息,conent={}", recMessageBody.getContent());
				} else if ("event".equals(contentMap.get("MsgType"))) {
					//Demo 只做logger 打印 实际中要做接收到event Message后的业务逻辑处理
					logger.info("消息为event Message 消息,conent={}", recMessageBody.getContent());
				} else {
					logger.error("message 消息类型不支持");
					return "failure";
				}
			}
			
			return "success";
		} else {
			logger.error("解析消息内容为空");
			return "failure";
		}
	}
	
}
