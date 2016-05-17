package com.facishare.open.demo.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.demo.beans.args.BindAccountArg;
import com.facishare.open.demo.beans.args.OpenUserIdArg;
import com.facishare.open.demo.beans.results.BindAccountResult;
import com.facishare.open.demo.beans.results.OpenUserIdResult;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.manager.AAAManager;
import com.facishare.open.demo.manager.AccessTokenManager;
import com.facishare.open.demo.utils.OpenAPIUtils;

@Service("aaaManager")
public class AAAManagerImpl implements AAAManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public OpenUserIdResult getOpenUserId(String code) throws AccessTokenException {
        OpenUserIdArg arg = new OpenUserIdArg();
        arg.setCode(code);
        arg.setAppAccessToken(accessTokenManager.getAppAccessToken());

        return OpenAPIUtils.getOpenUserId(arg);
    }

    @Override
    public BindAccountResult bindAccount(String openUserId, String appAccount) throws AccessTokenException {
        BindAccountArg bindAccountArg = new BindAccountArg();
        bindAccountArg.setOpenUserId(openUserId);
        bindAccountArg.setAppAccessToken(accessTokenManager.getAppAccessToken());
        bindAccountArg.setAppAccount(appAccount);
        return OpenAPIUtils.bindAccount(bindAccountArg);
    }

}
