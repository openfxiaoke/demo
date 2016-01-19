package com.facishare.open.third.manager;

import com.facishare.open.third.beans.results.CorpUserMapResult;

/**
 * 通讯录数据的管理接口
 * @author huanghp
 * @date 2015年9月18日
 */

public interface AddressBookManager {
	
	/**
	 * 获取公司所有人员信息的方法
	 * @param permanentCode 永久授权码
	 * @return @see CorpUserMapResult
	 */
	public CorpUserMapResult getCorpEmployeeMap(String permanentCode);
	
	/**
	 * 通过批量方式获取公司所有人员信息
	 * @param permanentCode 永久授权码
	 * @return @see CorpUserMapResult
	 */
	public CorpUserMapResult getCorpEmployeeMapByBatch(String permanentCode);
	
}
