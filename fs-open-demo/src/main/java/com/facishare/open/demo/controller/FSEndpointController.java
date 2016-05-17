package com.facishare.open.demo.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.facishare.open.demo.beans.AppReqParmVO;
import com.facishare.open.demo.beans.results.OpenUserIdResult;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.manager.AAAManager;
import com.facishare.open.demo.utils.Configuration;
import com.facishare.open.demo.utils.Constants;
import com.facishare.open.demo.utils.OpenAPIUtils;

@Controller
@RequestMapping("/fs")
public class FSEndpointController {

    private static final Logger LOG = LoggerFactory.getLogger(FSEndpointController.class);

    @Resource(name = "configuration")
    private Configuration configuration;

    @Autowired
    private AAAManager aaaManager;

    /**
     * 开平APP跳转或者Web跳转到企业应用入口处理方法
     * 
     * @param reqCodeForm @see ReqCodeFormVO
     * @param session @see HttpSession
     * @return
     */
    @RequestMapping("/endpoint")
    public String handle(AppReqParmVO appReqParmVO, HttpSession session, Map<String, Object> model) {
        // 跳转到首页
        String view = "../../index";

        // 验证第三方跳转传入参数是否完整
        if (appReqParmVO == null || StringUtils.isEmpty(appReqParmVO.getNonce())
                || StringUtils.isEmpty(appReqParmVO.getCode()) || StringUtils.isEmpty(appReqParmVO.getCodeSig())
                || StringUtils.isEmpty(appReqParmVO.getTimestamp())) {
            model.put("message", "请求参数不完整");
            return view;
        }

        // 验证时间戳格式
        if (!StringUtils.isNumeric(appReqParmVO.getTimestamp())) {
            model.put("message", "时间戳格式不对");
            return view;
        }

        long nowMills = System.currentTimeMillis();
        long timeMills = Long.parseLong(appReqParmVO.getTimestamp());

        // 当前时间上下50s 都认为是时间误差接受该请求
        if (Math.abs(nowMills - timeMills) > 500000) {
            model.put("message", "请求timestamp已经过期");
            return view;
        }

        // 请求参数+token 验证请求是否合法
        boolean verifyResult = OpenAPIUtils.verifyAppReq(appReqParmVO, configuration.getToken());
        if (!verifyResult) {
            model.put("message", "请求参数验证不通过");
            return view;
        }

        // 通过 code 获取跳转人员信息
        OpenUserIdResult openUserIdResult = null;
        try {
            openUserIdResult = aaaManager.getOpenUserId(appReqParmVO.getCode());
            session.setAttribute(Constants.SESSION_CURRENT_OPEN_USER_ID, openUserIdResult.getOpenUserId());
        } catch (AccessTokenException e) {
            LOG.error("access token error, details:", e);
            openUserIdResult = new OpenUserIdResult();
            openUserIdResult.setErrorCode(e.getCode());
            openUserIdResult.setErrorMessage(e.getMessage());
        }

        model.put("openUser", openUserIdResult);

        return view;
    }
}
