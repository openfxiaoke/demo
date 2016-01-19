package com.facishare.open.third.manager;

import java.util.Map;

import com.facishare.open.third.beans.results.TemplateMsgResult;
import com.facishare.open.third.beans.results.TextMsgResult;


/**
 * 消息管理的实现类
 * @author huanghp
 * @date 2015年8月28日
 */

public interface MessageManager {

	/**
	 * @param openUserIds 人员id列表字符串 用英文逗号隔开
	 * @param msgContent 消息内容
	 * @return
	 * @throws Exception
	 */
	public TextMsgResult sendTextMsg(String[] openUserIds, String msgContent) throws Exception ;
	
	
	/**
	 * @param openUserIds 人员id列表字符串 用英文逗号隔开
	 * @param url 模版消息跳转地址
	 * @param templateId 模版Id
	 * @param datas 模版消息数据体
	 * @return
	 * @throws Exception
	 */
	public TemplateMsgResult sendTempleteMsg(String[] openUserIds, String url, String templateId, Map<String,Map<String,String>> datas) throws Exception ;
	
}
