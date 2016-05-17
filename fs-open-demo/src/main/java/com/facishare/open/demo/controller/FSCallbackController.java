package com.facishare.open.demo.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.facishare.open.demo.beans.MsgReceiveParamVO;
import com.facishare.open.demo.beans.RecMessageBody;
import com.facishare.open.demo.beans.enums.RecMessageType;
import com.facishare.open.demo.utils.Configuration;
import com.facishare.open.demo.utils.OpenAPIUtils;
import com.facishare.open.demo.utils.SigUtils;
import com.google.gson.Gson;

@RestController
@RequestMapping("/fs")
public class FSCallbackController {

    private static final Logger LOG = LoggerFactory.getLogger(FSCallbackController.class);

    @Resource(name = "configuration")
    private Configuration configuration;

    /**
     * 接收开平推送过来的消息的方法
     * 
     * @param msgReceiveParamVO @see MsgReceiveParamVO
     * @return "success" 表示接收成功,其它表示失败 方法需保证在3秒内返回，否则开平认为推送失败会重试一次
     *         如果存在复杂的逻辑处理不能保证3秒内返回，需考虑先返回然后开线程去处理业务
     */
    @RequestMapping(value = "/callback", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String decode(@RequestBody MsgReceiveParamVO msgReceiveParamVO) {
        // 验证推送传入参数是否完整
        if (msgReceiveParamVO == null || StringUtils.isEmpty(msgReceiveParamVO.getNonce())
                || StringUtils.isEmpty(msgReceiveParamVO.getContent())
                || StringUtils.isEmpty(msgReceiveParamVO.getSig())
                || StringUtils.isEmpty(msgReceiveParamVO.getTimeStamp())) {
            LOG.error("illegal argument");
            return "failure";
        }

        // 验证时间戳格式
        if (!StringUtils.isNumeric(msgReceiveParamVO.getTimeStamp())) {
            LOG.error("timeStamp is illegal");
            return "failure";
        }

        long nowMills = System.currentTimeMillis();
        long timeMills = Long.parseLong(msgReceiveParamVO.getTimeStamp());

        // 当前时间上下50s 都认为是时间误差接受该请求
        if (Math.abs(nowMills - timeMills) > 500000) {
            LOG.error("timeStamp  expired ");
            return "failure";
        }

        // 传人参数+token 进行参数合法性验证
        boolean validateResult = OpenAPIUtils.verifyMsgReq(msgReceiveParamVO, configuration.getToken());
        if (!validateResult) {
            LOG.error(" verify signature error ");
            return "failure";
        }

        // 通过AESKey 对解密授权码
        String messageContent = SigUtils.decodeAES(msgReceiveParamVO.getContent(), configuration.getEncodingAesKey());

        // 具体请参照开平API文档 <接收消息与事件>章节
        Gson gson = new Gson();
        if (StringUtils.isNotEmpty(messageContent)) {
            RecMessageBody recMessageBody = gson.fromJson(messageContent, RecMessageBody.class);

            if (recMessageBody == null) {
                LOG.error("message body is illegal");
                return "failure";
            }

            if (!StringUtils.equals(recMessageBody.getType(), RecMessageType.CANCLEPERMANENTCODE.getTypeName())
                    && !StringUtils.equals(recMessageBody.getType(), RecMessageType.PERMANENTCODE.getTypeName())
                    && !StringUtils.equals(recMessageBody.getType(), RecMessageType.MESSAGE.getTypeName())) {
                LOG.error("message type is illegal");
                return "failure";
            }

            // 判断消息为PermanentCode 消息
            if (StringUtils.equals(recMessageBody.getType(), RecMessageType.PERMANENTCODE.getTypeName())) {
                // Demo 只做logger 打印 实际中要做接收到PermanentCode后的业务逻辑处理
                LOG.info("PermanentCode message,PermanentCode={}", recMessageBody.getContent());
                // 第三方应用接收到PermanentCode后，需要获取企业corpId，并保存对应的绑定关系
                configuration.setPermanentCode(recMessageBody.getContent());
            }

            // 判断消息为CanclePermanentCode 消息
            if (StringUtils.equals(recMessageBody.getType(), RecMessageType.CANCLEPERMANENTCODE.getTypeName())) {
                // Demo 只做logger 打印 实际中要做接收到CanclePermanentCode后的业务逻辑处理
                LOG.info("CanclePermanentCode message,PermanentCode={}", recMessageBody.getContent());
                // 第三方应用接收到PermanentCode后，需要解绑企业对应的绑定关系
            }

            // 判断消息为Message(包括文本和事件) 消息
            if (StringUtils.equals(recMessageBody.getType(), RecMessageType.MESSAGE.getTypeName())) {

                Map<String, String> contentMap = gson.fromJson(recMessageBody.getContent(), Map.class);

                if ("text".equals(contentMap.get("MsgType"))) {
                    // Demo 只做logger 打印 实际中要做接收到Text Message后的业务逻辑处理
                    LOG.info("Text Message,conent={}", recMessageBody.getContent());
                } else if ("event".equals(contentMap.get("MsgType"))) {
                    // Demo 只做logger 打印 实际中要做接收到event Message后的业务逻辑处理
                    LOG.info("event Message,conent={}", recMessageBody.getContent());
                } else {
                    LOG.error("message type is illegal");
                    return "failure";
                }
            }

            return "success";
        } else {
            LOG.error("message body is empty");
            return "failure";
        }
    }
}
