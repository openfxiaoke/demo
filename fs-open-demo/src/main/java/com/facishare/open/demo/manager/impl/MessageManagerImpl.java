package com.facishare.open.demo.manager.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.demo.beans.CorpAccessToken;
import com.facishare.open.demo.beans.args.TextMsgArg;
import com.facishare.open.demo.beans.args.TextMsgArg.Text;
import com.facishare.open.demo.beans.results.TextMsgResult;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.manager.AccessTokenManager;
import com.facishare.open.demo.manager.MessageManager;
import com.facishare.open.demo.utils.OpenAPIUtils;

@Service("messageManager")
public class MessageManagerImpl implements MessageManager {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Override
    public TextMsgResult sendTextMsg(List<String> openUserIds, String msgContent) throws AccessTokenException {
        TextMsgArg arg = new TextMsgArg();
        arg.setMsgType("text");
        arg.setToUser(openUserIds);
        Text text = arg.new Text();
        text.setContent(msgContent);
        arg.setText(text);
        CorpAccessToken token = accessTokenManager.getCorpAccessToken();
        arg.setCorpAccessToken(token.getCorpAccessToken());
        arg.setCorpId(token.getCorpId());
        return OpenAPIUtils.sendTextMsg(arg);
    }

}
