package com.facishare.open.third.utils;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.facishare.open.third.beans.HttpResponseMessageVO;
import com.facishare.open.third.beans.AppReqParmVO;
import com.facishare.open.third.beans.MsgReceiveParamVO;
import com.facishare.open.third.beans.args.AppTokenArg;
import com.facishare.open.third.beans.args.BatchUserListArg;
import com.facishare.open.third.beans.args.BindAccountArg;
import com.facishare.open.third.beans.args.CorpAccessTokenArg;
import com.facishare.open.third.beans.args.DeptListArg;
import com.facishare.open.third.beans.args.DeptUserListArg;
import com.facishare.open.third.beans.args.OpenUserIdArg;
import com.facishare.open.third.beans.args.UserInfoArg;
import com.facishare.open.third.beans.results.AppTokenResult;
import com.facishare.open.third.beans.results.BindAccountResult;
import com.facishare.open.third.beans.results.CorpAccessTokenResult;
import com.facishare.open.third.beans.results.DeptListResult;
import com.facishare.open.third.beans.results.DeptUserListResult;
import com.facishare.open.third.beans.results.OpenUserIdResult;
import com.facishare.open.third.beans.results.TemplateMsgResult;
import com.facishare.open.third.beans.results.TextMsgResult;
import com.facishare.open.third.beans.results.UserInfoResult;
import com.google.gson.Gson;

/**
 * 开放平台Api调用的工具类
 * @author huanghp
 * @date 2015年8月28日
 */

public class OpenAPIUtils {
	
	private static Logger logger = LoggerFactory.getLogger(OpenAPIUtils.class);
	
	/**
	 * API 请求路径
	 * SDE 环境为http://172.31.102.122
	 * FTE 环境为：https://open.fsfte2.com
	 * PRODUCE 环境为：https://open.fxiaoke.com
	 */
	private static String prefix = "https://open.fxiaoke.com";
	
	/**
	 * google json 解析器
	 */
	private static Gson gson = new Gson();
	
	/**
	 * 获取AppToken 实现
	 * @param appTokenArg @see AppTokenArg
	 * @return
	 * @throws Exception
	 */
	public static AppTokenResult getAppToken(AppTokenArg appTokenArg) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/cgi/appAccessToken/get", gson.toJson(appTokenArg));
		AppTokenResult appTokenResult = gson.fromJson(httpResponseMessage.getContent(), AppTokenResult.class);
		
		return appTokenResult;
	}
	
	/**
	 * 获取corpAccessToken 实现
	 * @param corpAccessTokenArg @see CorpAccessTokenArg
	 * @return
	 * @throws Exception
	 */
	public static CorpAccessTokenResult getCorpToken(CorpAccessTokenArg corpAccessTokenArg) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/cgi/corpAccessToken/get", gson.toJson(corpAccessTokenArg));
		CorpAccessTokenResult corpAccessTokenResult = gson.fromJson(httpResponseMessage.getContent(), CorpAccessTokenResult.class);
		
		return corpAccessTokenResult;
	}
	
	/**
	 * 获取OpenUserId 实现
	 * @param openUserIdArg @see OpenUserIdArg
	 * @return
	 * @throws Exception
	 */
	public static OpenUserIdResult getOpenUserId(OpenUserIdArg openUserIdArg) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/oauth2/openUserId/get", gson.toJson(openUserIdArg));
		OpenUserIdResult openUserIdResult = gson.fromJson(httpResponseMessage.getContent(), OpenUserIdResult.class);
		
		return openUserIdResult;
	}
	
	/**
	 * accountBind 实现
	 * @param bindAccountArg @see BindAccountArg
	 * @return
	 * @throws Exception
	 */
	public static BindAccountResult bindAccount(BindAccountArg bindAccountArg) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/oauth2/accountBind", gson.toJson(bindAccountArg));
		BindAccountResult bindAccountResult = gson.fromJson(httpResponseMessage.getContent(), BindAccountResult.class);
		
		return bindAccountResult;
	}
	
	/**
	 * 获取用户详细信息 实现
	 * @param appTokenArg @see UserInfoArg
	 * @return
	 * @throws Exception
	 */
	public static UserInfoResult getUserInfo(UserInfoArg userInfoArg) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/cgi/user/get", gson.toJson(userInfoArg));
		UserInfoResult userInfoResult = gson.fromJson(httpResponseMessage.getContent(), UserInfoResult.class);
		
		return userInfoResult;
	}
	
	/**
	 * 获取部门列表 实现
	 * @param deptListArg @see DeptListArg
	 * @return
	 * @throws Exception
	 */
	public static DeptListResult getDeptList(DeptListArg deptListArg) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/cgi/department/list", gson.toJson(deptListArg));
		DeptListResult deptListResult = gson.fromJson(httpResponseMessage.getContent(), DeptListResult.class);
		
		return deptListResult;
	}
	
	/**
	 * 获取部门人员列表 实现
	 * @param deptUserListArg @see DeptUserListArg
	 * @return
	 * @throws Exception
	 */
	public static DeptUserListResult getDeptUserList(DeptUserListArg deptUserListArg) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/cgi/user/list", gson.toJson(deptUserListArg));
		DeptUserListResult deptUserListResult = gson.fromJson(httpResponseMessage.getContent(), DeptUserListResult.class);
		
		return deptUserListResult;
	}
	
	/**
	 * 批量获取部门人员列表 实现
	 * @param batchUserListArg @see BatchUserListArg
	 * @return
	 * @throws Exception
	 */
	public static DeptUserListResult getDeptUserListByBatch(BatchUserListArg batchUserListArg) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/cgi/user/batch", gson.toJson(batchUserListArg));
		DeptUserListResult deptUserListResult = gson.fromJson(httpResponseMessage.getContent(), DeptUserListResult.class);
		
		return deptUserListResult;
	}
	
	/**
	 * 发送文本消息 实现
	 * @param msgMap 消息体内容
	 * @return
	 * @throws Exception
	 */
	public static TextMsgResult sendTextMsg(Map<String,Object> msgMap) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/cgi/message/send", gson.toJson(msgMap));
		TextMsgResult textMsgResult = gson.fromJson(httpResponseMessage.getContent(), TextMsgResult.class);
		
		return textMsgResult;
	}
	
	/**
	 * 发送模版消息 实现
	 * @param msgMap 消息体内容
	 * @return
	 * @throws Exception
	 */
	public static TemplateMsgResult sendTemplateMsg(Map<String,Object> msgMap) throws Exception {
		HttpResponseMessageVO httpResponseMessage = HttpTookit.sendPostByJson(prefix + "/cgi/message/send", gson.toJson(msgMap));
		TemplateMsgResult templateMsgResult = gson.fromJson(httpResponseMessage.getContent(), TemplateMsgResult.class);
		
		return templateMsgResult;
	}
	
	/**
	 * 验证消息推送请求合法性
	 * @param msgReceiveParamVO @see MsgReceiveParamVO
	 * @param token
	 * @return true 表示验证成功,其它表示失败
	 */
	public static boolean verifyMsgReq(MsgReceiveParamVO msgReceiveParamVO,String token) {
		boolean verifyResult = false;
		
		try {
			String sha1Str = SigUtils.getSHA1(token, msgReceiveParamVO.getTimeStamp(), msgReceiveParamVO.getNonce(), msgReceiveParamVO.getContent());
			verifyResult = sha1Str.equals(msgReceiveParamVO.getSig()) ? true:false;
		} catch(Exception e) {
			verifyResult = false;
			logger.error("验证推送消息参数签名异常{}", e);
		}
		
		return verifyResult;
	}
	
	/**
	 * 验证应用跳转来源合法性
	 * @param appReqParmVO @see AppReqParmVO
	 * @param token
	 * @return true 表示验证成功,其它表示失败
	 */
	public static boolean verifyAppReq(AppReqParmVO appReqParmVO, String token) {
		boolean verifyResult = false;
		try {
			String[] array = new String[] {appReqParmVO.getCode(), token, appReqParmVO.getTimestamp(), appReqParmVO.getNonce()};
			
			Arrays.sort(array);
			
			StringBuffer sb = new StringBuffer();
			for(String temp: array) {
				sb.append(temp);
			}
			
			String signResult = DigestUtils.sha1Hex(sb.toString());
			verifyResult = appReqParmVO.getCodeSig().equals(signResult) ? true : false;
		} catch(Exception e) {
			verifyResult = false;
			logger.error("验证应用跳转登录异常{}", e);
		}
		
		return verifyResult;
	}
	
	public static void main(String[] args) throws Exception {
		DeptUserListArg deptUserListArg = new DeptUserListArg();
		deptUserListArg.setCorpAccessToken("E9D007B75DFBFDDC094D37F5B396C500");
		deptUserListArg.setCorpId("FSCID_829F6665F559CF3791409D2C51A7D7AF");
		deptUserListArg.setDepartmentId(1299);
				
		DeptUserListResult DeptUserListResult  = OpenAPIUtils.getDeptUserList(deptUserListArg);
		System.out.println(DeptUserListResult.getErrorCode());
		System.out.println(DeptUserListResult.getErrorMessage());

		System.out.println(DeptUserListResult.getUserList());
		
	}
	
}
