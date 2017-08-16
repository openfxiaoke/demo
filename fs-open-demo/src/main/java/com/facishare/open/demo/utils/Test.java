package com.facishare.open.demo.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.facishare.open.demo.beans.args.CrmDescArg;
import com.facishare.open.demo.beans.args.CrmQueryArg;
import com.facishare.open.demo.beans.results.CrmDescResult;
import com.facishare.open.demo.beans.results.CrmQueryResult;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

public class Test {

	/**
	 * 级联的与普通的是一样的，value递增
	 * @param dataDesc
	 * @param fieldName
	 * @return
	 */
	public static Map<String, String> genOptionsMap(
			Map<String, Map<String, Object>> dataDesc, String fieldName) {
		Map<String, String> optionsMap = Maps.newHashMap();
		Map<?, ?> fieldDescMap = (Map<?, ?>) dataDesc.get("fields").get(
				fieldName);
		if (fieldDescMap == null || fieldDescMap.isEmpty()) {
			return optionsMap;
		}
		Object options = fieldDescMap.get("options");
		if (options != null) {
			List<Map<String, Object>> optionList = (List<Map<String, Object>>) options;
			if (optionList != null && !optionList.isEmpty()) {
				for (Map<String, Object> option : optionList) {
					optionsMap.put((String) option.get("value"),
							(String) option.get("label"));
					List<Map<String, Object>> childOptions = (List<Map<String, Object>>) option
							.get("child_options");
					if (childOptions != null) {
						for (Map<String, Object> item : childOptions) {
							optionsMap.put((String) item.get("value"),
									(String) item.get("label"));
						}
					}
				}
			}
		}
		return optionsMap;
	}

	public static String genOptionLabelWithChild(Map<String, Map<String, Object>> dataDesc,
			String fieldName, String value) {
		Map<?, ?> fieldDescMap = (Map<?, ?>) dataDesc.get("fields").get(fieldName);
		if (fieldDescMap == null || fieldDescMap.isEmpty()) {
			return "";
		}
		Object options = fieldDescMap.get("options");
		if (options == null) {
			return "";
		}
		List<Map<String, Object>> optionList = (List<Map<String, Object>>) options;
		if (optionList != null && !optionList.isEmpty()) {
			for (Map<String, Object> option : optionList) {
				String label = (String) option.get("label");
				List<Map<String, Object>> childOptions = (List<Map<String, Object>>) option.get("child_options");
				if (childOptions != null) {
					for (Map<String, Object> item : childOptions) {
						if (value.equals((String) item.get("value"))) {
							return label.concat("/").concat((String) item.get("label"));
						}
					}
				}
			}
		}
		return "";
	}
	
	public static String genOptionLabel(Map<String, Map<String, Object>> dataDesc,
			String fieldName, String value) {
		Map<?, ?> fieldDescMap = (Map<?, ?>) dataDesc.get("fields").get(fieldName);
		if (fieldDescMap == null || fieldDescMap.isEmpty()) {
			return "";
		}
		Object options = fieldDescMap.get("options");
		if (options == null) {
			return "";
		}
		List<Map<String, Object>> optionList = (List<Map<String, Object>>) options;
		if (optionList != null && !optionList.isEmpty()) {
			for (Map<String, Object> option : optionList) {
				String label = (String) option.get("label");
				String value1 = (String) option.get("value");
				if(value.equals(value1)){
					return label;
				}
			}
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
		//纷享openUserId
		String openUserId = "";
		String apiName = "OpportunityObj";// /todo
		long beginTime = 4522233333l;// todo
		String corpAccessToken = "";// todo
		String corpId = "";// todo
		Gson gson = new Gson();
		
		CrmQueryArg arg = new CrmQueryArg();
		arg.setCurrentOpenUserId(openUserId);
		arg.setApiName(apiName);
		CrmQueryArg.RangeCondition rangeCondition = new CrmQueryArg.RangeCondition();
		rangeCondition.setFrom(beginTime);
		CrmQueryArg.DataProjection dataProjection = new CrmQueryArg.DataProjection();
		CrmQueryArg.Order order = new CrmQueryArg.Order();

		CrmQueryArg.SearchQuery searchQuery = new CrmQueryArg.SearchQuery();
		searchQuery.setRangeConditions(Collections.singletonList(rangeCondition));
		searchQuery.setDataProjection(dataProjection);
		searchQuery.setOrders(Collections.singletonList(order));
		arg.setSearchQuery(searchQuery);
		arg.setCorpAccessToken(corpAccessToken);
		arg.setCorpId(corpId);
		CrmQueryResult crmQueryResult = OpenAPIUtils.queryCrmData(arg);
		
		System.out.println(gson.toJson(crmQueryResult));

		apiName = "OpportunityObj";// /todo
		CrmDescArg descArg = new CrmDescArg();
		descArg.setApiName(apiName);
		descArg.setCurrentOpenUserId(openUserId);
		descArg.setCorpAccessToken(corpAccessToken);
		descArg.setCorpId(corpId);
		CrmDescResult crmDescResult = OpenAPIUtils.getCrmDesc(descArg);
		System.out.println(gson.toJson(crmDescResult));
		
		//一级选项
		System.out.println(gson.toJson(genOptionLabel(crmDescResult.getObjectDesc(),"UDMSel1__c","1")));
		
		//二级联动选项
		System.out.println(gson.toJson(genOptionLabelWithChild(crmDescResult.getObjectDesc(),"UDCSSel1__c","2")));
	}
}
