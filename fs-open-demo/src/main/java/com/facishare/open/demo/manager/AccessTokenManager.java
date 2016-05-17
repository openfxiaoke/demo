package com.facishare.open.demo.manager;

import com.facishare.open.demo.beans.CorpAccessToken;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.exception.AppAccessTokenRequestException;

/**
 * AccessToken缓存管理接口
 * @author gaoshengbo
 *
 */
public interface AccessTokenManager {

    /**
     * 重置缓存中的AppAccessToken
     * 
     * @throws AppAccessTokenRequestException
     */
    public void resetAppAccessToken() throws AppAccessTokenRequestException;

    /**
     * 重置缓存中的CorpAccessToken
     * 
     * @throws AccessTokenException
     */
    public void resetCorpAccessToken() throws AccessTokenException;

    /**
     * AppAccessToken是企业应用的全局唯一票据，需要AppId和AppSecret来换取， 不同的AppSecret会返回不同的AppAccessToken。
     * 正常情况下AppAccessToken有效期为2592000秒（30天）， 有效期内重复获取返回相同结果，并自动续期。 企业需要做适当缓存
     * 
     * @return
     */
    public String getAppAccessToken() throws AppAccessTokenRequestException;

    /**
     * CorpAccessToken是企业应用访问相应公司数据的全局唯一票据， 拉取信息和发送消息的接口都需要携带CorpAccessToken和CorpId。
     * 正常情况下CorpAccessToken的有效期为7200秒，有效期内重复获取返回相同结果，并自动续期。 企业需要做适当缓存
     * 
     * @return
     */
    public CorpAccessToken getCorpAccessToken() throws AccessTokenException;

}
