package com.facishare.open.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.facishare.open.demo.beans.results.BaseResult;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.manager.AccessTokenManager;
import com.facishare.open.demo.utils.Constants.interfaceResponseCode;

public class AccessTokenExpiredExceptionAspect {

    private static final Logger LOG = LoggerFactory.getLogger(AccessTokenExpiredExceptionAspect.class);

    @Autowired
    private AccessTokenManager accessTokenManager;

    /**
     * 返回结果拦截，如果是token过期，清理token缓存
     * 
     * @param joinPoint
     * @param result
     */
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        if (result instanceof BaseResult) {
            BaseResult baseResult = (BaseResult) result;
            try {
                if (baseResult.getErrorCode() == interfaceResponseCode.APP_ACCESS_TOKEN_EXPIRED.code) {
                    accessTokenManager.resetAppAccessToken();
                    accessTokenManager.resetCorpAccessToken();
                } else if (baseResult.getErrorCode() == interfaceResponseCode.CORP_ACCESS_TOKEN_EXPIRED.code) {
                    accessTokenManager.resetCorpAccessToken();
                }
            } catch (Exception e) {
                LOG.error("afterReturn error, service:{} method:{}, details:", typeName, methodName, e);
            }
        }
    }

    /**
     * 异常拦截，如果是AccessTokenException异常，清理token缓存
     * 
     * @param joinPoint
     * @param e
     */
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        if (e instanceof AccessTokenException) {
            try {
                accessTokenManager.resetCorpAccessToken();
            } catch (AccessTokenException ae) {
                LOG.error("afterThrowing error, service:{} method:{}, details:", typeName, methodName, ae);
            }
        }
    }

}
