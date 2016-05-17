package com.facishare.open.demo.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.demo.beans.CorpAccessToken;
import com.facishare.open.demo.beans.EmployeeVO;
import com.facishare.open.demo.beans.args.DeptAddModifyArg;
import com.facishare.open.demo.beans.args.DeptListArg;
import com.facishare.open.demo.beans.args.DeptUserListArg;
import com.facishare.open.demo.beans.args.UserInfoArg;
import com.facishare.open.demo.beans.results.CorpUserMapResult;
import com.facishare.open.demo.beans.results.Department;
import com.facishare.open.demo.beans.results.DeptAddResult;
import com.facishare.open.demo.beans.results.DeptListResult;
import com.facishare.open.demo.beans.results.DeptUpdateResult;
import com.facishare.open.demo.beans.results.DeptUserListResult;
import com.facishare.open.demo.beans.results.User;
import com.facishare.open.demo.beans.results.UserResult;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.manager.AccessTokenManager;
import com.facishare.open.demo.manager.AddressBookManager;
import com.facishare.open.demo.utils.OpenAPIUtils;
import com.google.common.collect.Maps;

@Service("addressBookManager")
public class AddressBookManagerImpl implements AddressBookManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    /**
     * 
     * @param deptIds 部门id列表
     * @param deptMap 部门id和名称的Map
     * @return 部门名称英文逗号分割
     */
    private static String parsDepartmentNames(List<Integer> deptIds, Map<Integer, String> deptMap) {
        StringBuilder sb = new StringBuilder("");

        if (deptIds != null && !deptIds.isEmpty()) {
            for (Integer deptId : deptIds) {
                sb.append(deptMap.get(deptId)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    @Override
    public CorpUserMapResult getCorpEmployeeMap() throws AccessTokenException {
        CorpUserMapResult corpUserMapResult = new CorpUserMapResult();

        Map<String, EmployeeVO> employMap = Maps.newHashMap();
        Map<Integer, String> deptMap = Maps.newHashMap();

        DeptListArg arg = new DeptListArg();
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        DeptListResult deptListResult = OpenAPIUtils.getDeptList(arg);

        if (deptListResult.getErrorCode() != 0) {
            return corpUserMapResult;
        }

        List<Department> deptList = deptListResult.getDepartments();
        if (deptList == null || deptList.isEmpty()) {
            corpUserMapResult.setErrorMessage("公司部门为空");
            return corpUserMapResult;
        }

        // 部门Id到名称对应表，用于去重和id->name 转换
        for (Department deptResult : deptList) {
            deptMap.put(deptResult.getId(), deptResult.getName());
        }

        DeptUserListArg deptUserListArg = new DeptUserListArg();
        deptUserListArg.setCorpAccessToken(token.getCorpAccessToken());
        deptUserListArg.setCorpId(token.getCorpId());
        // 迭代部门获取部门人员
        for (Department deptResult : deptList) {
            deptUserListArg.setDepartmentId(deptResult.getId());
            // deptUserListArg.fetchChild 默认为false 不获取子部门的人员信息
            DeptUserListResult deptUserListResult = OpenAPIUtils.getDeptUserList(deptUserListArg);

            if (deptUserListResult.getErrorCode() != 0) {
                continue;
            }

            List<User> userInfoList = deptUserListResult.getUserList();

            if (userInfoList == null || userInfoList.isEmpty()) {
                continue;
            }

            for (User userInfo : userInfoList) {
                EmployeeVO employeeInfo = new EmployeeVO();
                employeeInfo.setOpenId(userInfo.getOpenUserId());
                employeeInfo.setPhone(userInfo.getMobile());
                employeeInfo.setPosition(userInfo.getPosition());
                employeeInfo.setGender(userInfo.getGender());
                employeeInfo.setName(userInfo.getName());
                employeeInfo.setFullName(userInfo.getFullName());
                employeeInfo.setDepartment(parsDepartmentNames(userInfo.getDepartmentIds(), deptMap));
                employMap.put(employeeInfo.getOpenId(), employeeInfo);
            }
        }

        corpUserMapResult.setErrorMessage("ok");
        corpUserMapResult.setTotalDepartment(deptList.size());
        corpUserMapResult.setTotalMember(employMap.size());
        corpUserMapResult.setCorpUserMap(employMap);

        return corpUserMapResult;
    }

    @Override
    public DeptAddResult addDept(Department department) throws AccessTokenException {
        DeptAddModifyArg arg = new DeptAddModifyArg();
        arg.setDepartment(department);

        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());

        return OpenAPIUtils.addDept(arg);
    }

    @Override
    public DeptListResult getDeptList() throws AccessTokenException {
        DeptListArg arg = new DeptListArg();
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());

        return OpenAPIUtils.getDeptList(arg);
    }

    @Override
    public DeptUpdateResult modifyDept(Department department) throws AccessTokenException {
        DeptAddModifyArg arg = new DeptAddModifyArg();
        arg.setDepartment(department);

        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());

        return OpenAPIUtils.modifyDept(arg);
    }

    @Override
    public UserResult getUserInfo(String openUserId) throws AccessTokenException {
        UserInfoArg arg = new UserInfoArg();
        arg.setOpenUserId(openUserId);

        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());

        return OpenAPIUtils.getUserInfo(arg);
    }

}
