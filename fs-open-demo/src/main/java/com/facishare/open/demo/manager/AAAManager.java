package com.facishare.open.demo.manager;

import com.facishare.open.demo.beans.results.BindAccountResult;
import com.facishare.open.demo.beans.results.OpenUserIdResult;
import com.facishare.open.demo.exception.AccessTokenException;

/**
 * authentication、authorization管理接口
 * @author gaoshengbo
 *
 */
public interface AAAManager {

    /**
     * 根据code获取纷享开放平台openUserId
     * @param code 纷享开放平台下发的员工身份临时票据，有效期为十分钟，有效期内使用一次后则会过期
     * @return
     * @throws AccessTokenException
     */
    public OpenUserIdResult getOpenUserId(String code) throws AccessTokenException;

    /**
     * 企业应用帐号绑定
     * @param openUserId 纷享开放平台派发的用户账号
     * @param appAccount 企业应用系统里的员工帐号
     * @return
     * @throws AccessTokenException
     */
    public BindAccountResult bindAccount(String openUserId, String appAccount) throws AccessTokenException;

}
