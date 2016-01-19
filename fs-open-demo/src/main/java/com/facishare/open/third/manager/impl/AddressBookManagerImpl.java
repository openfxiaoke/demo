package com.facishare.open.third.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.facishare.open.third.beans.EmployeeVO;
import com.facishare.open.third.beans.args.AppTokenArg;
import com.facishare.open.third.beans.args.BatchUserListArg;
import com.facishare.open.third.beans.args.CorpAccessTokenArg;
import com.facishare.open.third.beans.args.DeptListArg;
import com.facishare.open.third.beans.args.DeptUserListArg;
import com.facishare.open.third.beans.results.AppTokenResult;
import com.facishare.open.third.beans.results.CorpAccessTokenResult;
import com.facishare.open.third.beans.results.CorpUserMapResult;
import com.facishare.open.third.beans.results.DepartmentResult;
import com.facishare.open.third.beans.results.DeptListResult;
import com.facishare.open.third.beans.results.DeptUserListResult;
import com.facishare.open.third.beans.results.UserInfoResult;
import com.facishare.open.third.manager.AddressBookManager;
import com.facishare.open.third.utils.OpenAPIUtils;

@Service("addressBookManager")
public class AddressBookManagerImpl implements AddressBookManager{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "configManager")
	private ConfigManagerImpl configManager;
	
	public CorpUserMapResult getCorpEmployeeMap(String permanentCode) {
		CorpUserMapResult corpUserMapResult = new CorpUserMapResult();
		
		Map<String, EmployeeVO> employMap = new HashMap<String, EmployeeVO>();
		Map<Integer, String> deptMap = new HashMap<Integer, String>();
		
		try {		
			AppTokenArg appTokenArg = new AppTokenArg();
			appTokenArg.setAppId(configManager.getAppId());
			appTokenArg.setAppSecret(configManager.getAppSecret());
			AppTokenResult appTokenResult = OpenAPIUtils.getAppToken(appTokenArg);
			
			if (appTokenResult == null || appTokenResult.getErrorCode() != 0) {
				corpUserMapResult.setErrorCode(appTokenResult.getErrorCode());
				corpUserMapResult.setErrorMessage(appTokenResult.getErrorMessage());
				
				return corpUserMapResult;
			}
			
			CorpAccessTokenArg corpAccessTokenArg = new CorpAccessTokenArg();
			corpAccessTokenArg.setAppAccessToken(appTokenResult.getAppAccessToken());
			corpAccessTokenArg.setPermanentCode(permanentCode);
			CorpAccessTokenResult corpAccessTokenResult = OpenAPIUtils.getCorpToken(corpAccessTokenArg);
			
			if (corpAccessTokenResult == null || corpAccessTokenResult.getErrorCode() != 0) {
				corpUserMapResult.setErrorCode(appTokenResult.getErrorCode());
				corpUserMapResult.setErrorMessage(appTokenResult.getErrorMessage());
				
				return corpUserMapResult;
			}
			
			DeptListArg deptListArg = new DeptListArg();
			deptListArg.setCorpAccessToken(corpAccessTokenResult.getCorpAccessToken());
			deptListArg.setCorpId(corpAccessTokenResult.getCorpId());
			DeptListResult deptListResult = OpenAPIUtils.getDeptList(deptListArg);
			
			if (deptListResult == null || deptListResult.getErrorCode() != 0) {
				corpUserMapResult.setErrorCode(appTokenResult.getErrorCode());
				corpUserMapResult.setErrorMessage(appTokenResult.getErrorMessage());
				
				return corpUserMapResult;
			}
			
			List<DepartmentResult> deptList = deptListResult.getDepartments();
			if (deptList == null || deptList.size() == 0) {
				corpUserMapResult.setErrorCode(appTokenResult.getErrorCode());
				corpUserMapResult.setErrorMessage("公司部门为空");
				
				return corpUserMapResult;
			}
			
			//部门Id到名称对应表，用于去重和id->name 转换
			for (DepartmentResult deptResult : deptList) {
				deptMap.put(deptResult.getId(), deptResult.getName());
			}
			
			//迭代部门获取部门人员
			for (DepartmentResult deptResult : deptList) {
				DeptUserListArg deptUserListArg = new DeptUserListArg();
				deptUserListArg.setCorpAccessToken(corpAccessTokenResult.getCorpAccessToken());
				deptUserListArg.setCorpId(corpAccessTokenResult.getCorpId());
				deptUserListArg.setDepartmentId(deptResult.getId());
				
				//deptUserListArg.fetchChild 默认为false 不获取子部门的人员信息
				DeptUserListResult deptUserListResult = OpenAPIUtils.getDeptUserList(deptUserListArg);
								
				if (deptUserListResult != null && deptUserListResult.getErrorCode() == 0) {
					List<UserInfoResult> userInfoList = deptUserListResult.getUserList();
					
					if (userInfoList != null && userInfoList.size() > 0) {
						for (UserInfoResult userInfo : userInfoList) {
							EmployeeVO employeeInfo = new EmployeeVO();
							employeeInfo.setOpenID(userInfo.getOpenUserId());
							employeeInfo.setPhone(userInfo.getMobile());
							employeeInfo.setPosition(userInfo.getPosition());
							employeeInfo.setGender(userInfo.getGender());
							employeeInfo.setDepartment(parsDepartmentNames(userInfo.getDepartmentIds(), deptMap));
							employMap.put(employeeInfo.getOpenID(), employeeInfo);
						}
					}
				}
			}
			
			corpUserMapResult.setErrorMessage("ok");
			corpUserMapResult.setTotalDepartment(deptList.size());
			corpUserMapResult.setTotalMember(employMap.size());
			corpUserMapResult.setCorpUserMap(employMap);
			
		} catch (Exception e) {
			logger.error("获取公司人员异常", e);
		}
		
		return corpUserMapResult;
	}
	
	public CorpUserMapResult getCorpEmployeeMapByBatch(String permanentCode) {
		CorpUserMapResult corpUserMapResult = new CorpUserMapResult();
		
		Map<String, EmployeeVO> employMap = new HashMap<String, EmployeeVO>();
		Map<Integer, String> deptMap = new HashMap<Integer, String>();
		
		try {		
			AppTokenArg appTokenArg = new AppTokenArg();
			appTokenArg.setAppId(configManager.getAppId());
			appTokenArg.setAppSecret(configManager.getAppSecret());
			AppTokenResult appTokenResult = OpenAPIUtils.getAppToken(appTokenArg);
			
			if (appTokenResult == null || appTokenResult.getErrorCode() != 0) {
				corpUserMapResult.setErrorCode(appTokenResult.getErrorCode());
				corpUserMapResult.setErrorMessage(appTokenResult.getErrorMessage());
				
				return corpUserMapResult;
			}
			
			CorpAccessTokenArg corpAccessTokenArg = new CorpAccessTokenArg();
			corpAccessTokenArg.setAppAccessToken(appTokenResult.getAppAccessToken());
			corpAccessTokenArg.setPermanentCode(permanentCode);
			CorpAccessTokenResult corpAccessTokenResult = OpenAPIUtils.getCorpToken(corpAccessTokenArg);
			
			if (corpAccessTokenResult == null || corpAccessTokenResult.getErrorCode() != 0) {
				corpUserMapResult.setErrorCode(appTokenResult.getErrorCode());
				corpUserMapResult.setErrorMessage(appTokenResult.getErrorMessage());
				
				return corpUserMapResult;
			}
			
			DeptListArg deptListArg = new DeptListArg();
			deptListArg.setCorpAccessToken(corpAccessTokenResult.getCorpAccessToken());
			deptListArg.setCorpId(corpAccessTokenResult.getCorpId());
			DeptListResult deptListResult = OpenAPIUtils.getDeptList(deptListArg);
			
			if (deptListResult == null || deptListResult.getErrorCode() != 0) {
				corpUserMapResult.setErrorCode(appTokenResult.getErrorCode());
				corpUserMapResult.setErrorMessage(appTokenResult.getErrorMessage());
				
				return corpUserMapResult;
			}
			
			List<DepartmentResult> deptList = deptListResult.getDepartments();
			if (deptList == null || deptList.size() == 0) {
				corpUserMapResult.setErrorCode(appTokenResult.getErrorCode());
				corpUserMapResult.setErrorMessage("公司部门为空");
				
				return corpUserMapResult;
			}
			
			List<List<Integer>> deptIdsList = new ArrayList<List<Integer>>();
			int i=0;
			List<Integer> partIds = null;
			
			//根据所有部门列表算出查询批次，批量查询每次最多100个部门id
			for (DepartmentResult deptResult : deptList) {
				if((i%100) == 0) {
					partIds = new ArrayList<Integer>();
					deptIdsList.add(partIds);
				}
				
				partIds.add(deptResult.getId());
				
				deptMap.put(deptResult.getId(), deptResult.getName());
				
				i++;
			}
			
			//迭代部门获取部门人员
			for (List<Integer> batchIds : deptIdsList) {
				BatchUserListArg batchUserListArg = new BatchUserListArg();
				batchUserListArg.setCorpAccessToken(corpAccessTokenResult.getCorpAccessToken());
				batchUserListArg.setCorpId(corpAccessTokenResult.getCorpId());
				batchUserListArg.setDepartmentIds(batchIds);
				
				DeptUserListResult deptUserListResult = OpenAPIUtils.getDeptUserListByBatch(batchUserListArg);
				
				if (deptUserListResult != null && deptUserListResult.getErrorCode() == 0) {
					List<UserInfoResult> userInfoList = deptUserListResult.getUserList();
					
					if (userInfoList != null && userInfoList.size() > 0) {
						for (UserInfoResult userInfo : userInfoList) {
							EmployeeVO employeeInfo = new EmployeeVO();
							employeeInfo.setOpenID(userInfo.getOpenUserId());
							employeeInfo.setPhone(userInfo.getMobile());
							employeeInfo.setPosition(userInfo.getPosition());
							employeeInfo.setGender(userInfo.getGender());
							employeeInfo.setDepartment(parsDepartmentNames(userInfo.getDepartmentIds(), deptMap));
							employMap.put(employeeInfo.getOpenID(), employeeInfo);
						}
					}
				}
			}
			
			corpUserMapResult.setErrorMessage("ok");
			corpUserMapResult.setTotalDepartment(deptList.size());
			corpUserMapResult.setTotalMember(employMap.size());
			corpUserMapResult.setCorpUserMap(employMap);
		} catch (Exception e) {
			logger.error("批量获取公司人员异常", e);
		}
		
		return corpUserMapResult;
	}

	/**
	 * 
	 * @param deptIds 部门id列表
	 * @param deptMap 部门id和名称的Map
	 * @return 部门名称英文逗号分割
	 */
	private String parsDepartmentNames(List<Integer> deptIds, Map<Integer, String> deptMap) {
		StringBuffer sb = new StringBuffer("");
		
		if (deptIds != null && deptIds.size() > 0) {
			for (Integer deptId : deptIds) {
				sb.append(deptMap.get(deptId)).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		
		return sb.toString();
	}
	
}
