package com.facishare.open.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.facishare.open.demo.beans.results.BindAccountResult;
import com.facishare.open.demo.beans.results.OpenUserIdResult;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.manager.AAAManager;
import com.facishare.open.demo.utils.Constants;

/**
 * 接收外部请求 Controller
 * 
 * @author gaoshengbo
 * @date 2016年5月3日
 */
@Controller
@RequestMapping("/aaa")
public class AAAController {

    private static final Logger LOG = LoggerFactory.getLogger(AAAController.class);

    @Autowired
    private AAAManager aaaManager;

    /**
     * 开平重定向进行调用(回调接口) 浏览器跳转到注册页面处理完后的回调入口
     * 
     * @param code 开放平台传过来的用户身份临时票据
     * @param state 第三方跳转到开放平台所生成的随机数用于验证请求
     * @param session @see HttpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/register")
    public String register(String code, String state, HttpSession session, Map<String, Object> model) {
        String view = "callbackResult";

        if (StringUtils.isBlank(code) || StringUtils.isBlank(state)) {
            model.put("message", "请求参数不完整");
            return view;
        }

        String sessionStat = (String) session.getAttribute("tempState");

        session.removeAttribute("tempState");

        // Demo简单用一次性session 中state对应来验证来源，实际中看情况采取验证策略
        if (!state.equals(sessionStat)) {
            model.put("message", "请求来源state不符合");
            return view;
        }

        // 通过 code 获取跳转人员信息
        OpenUserIdResult openUserIdResult;
        try {
            openUserIdResult = aaaManager.getOpenUserId(code);
            session.setAttribute(Constants.SESSION_CURRENT_OPEN_USER_ID, openUserIdResult.getOpenUserId());
        } catch (AccessTokenException e) {
            LOG.error("access token error, details:", e);
            openUserIdResult = new OpenUserIdResult();
            openUserIdResult.setErrorCode(e.getCode());
            openUserIdResult.setErrorMessage(e.getMessage());
        }

        if (openUserIdResult.getErrorCode() != 0) {
            model.put("message", "获取openUserId错误：errcode=" + openUserIdResult.getErrorCode() + "errmsg="
                    + openUserIdResult.getErrorMessage());
            return view;
        }

        // TODO: Demo 中由于没有实际的用户所以简单模拟，实际业务系统中要用真实账户做绑定建对应关系
        String appAccount = "test";

        BindAccountResult bindAccountResult = null;
        try {
            bindAccountResult = aaaManager.bindAccount(openUserIdResult.getOpenUserId(), appAccount);
        } catch (AccessTokenException e) {
            bindAccountResult = new BindAccountResult();
            bindAccountResult.setErrorCode(e.getCode());
            bindAccountResult.setErrorMessage(e.getMessage());
            LOG.error("access token error, details:", e);
        }

        model.put("bindUser", bindAccountResult);

        return view;
    }

    /**
     * 开平重定向进行调用(回调接口) 浏览器跳转到登陆页面处理完后的回调入口
     * 
     * @param code 开放平台传过来的用户身份临时票据
     * @param state 第三方跳转到开放平台所生成的随机数用于验证请求
     * @param session @see HttpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(String code, String state, HttpSession session, Map<String, Object> model) {
        String view = "callbackResult";

        if (StringUtils.isBlank(code) || StringUtils.isBlank(state)) {
            model.put("message", "请求参数不完整");
            return view;
        }

        String sessionStat = (String) session.getAttribute("tempState");

        session.removeAttribute("tempState");

        // Demo简单用一次性session 中state对应来验证来源，实际中看情况采取验证策略
        if (!state.equals(sessionStat)) {
            model.put("message", "请求来源state不符合");
            return view;
        }

        // 通过 code 获取跳转人员信息
        OpenUserIdResult openUserIdResult = null;
        try {
            openUserIdResult = aaaManager.getOpenUserId(code);
            session.setAttribute(Constants.SESSION_CURRENT_OPEN_USER_ID, openUserIdResult.getOpenUserId());
        } catch (AccessTokenException e) {
            LOG.error("access token error, details:", e);
            openUserIdResult = new OpenUserIdResult();
            openUserIdResult.setErrorCode(e.getCode());
            openUserIdResult.setErrorMessage(e.getMessage());
        }

        if (openUserIdResult.getErrorCode() != 0) {
            model.put("message", "获取openUserId错误：errcode=" + openUserIdResult.getErrorCode() + "errmsg="
                    + openUserIdResult.getErrorMessage());
            return view;
        }

        // 处理本系统业务,todo

        model.put("openUser", openUserIdResult);

        return view;
    }

}
