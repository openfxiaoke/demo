package com.facishare.open.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.facishare.open.demo.beans.results.TextMsgResult;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.manager.MessageManager;

@RestController
@RequestMapping(value = "/message", produces = {"application/json", "application/xml"})
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageManager messageManager;

    /**
     * 调用开平CGI接口给用户发送文本消息
     * 
     * @param msgContent 文本消息内容
     * @param openUserIds 人员Id 用英文,隔开
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public TextMsgResult sendTetxMessage(@RequestBody Map<String, Object> form) {
        String msgContent = (String) form.get("msgContent");
        List<String> openUserIds = (List<String>) form.get("openUserIds");

        TextMsgResult result;

        try {
            result = messageManager.sendTextMsg(openUserIds, msgContent);
        } catch (AccessTokenException e) {
            LOG.error("sendTetxMessage access token error, details:", e);
            result = new TextMsgResult();
            result.setErrorCode(e.getCode());
            result.setErrorMessage(e.getMessage());
        }

        return result;
    }
}
