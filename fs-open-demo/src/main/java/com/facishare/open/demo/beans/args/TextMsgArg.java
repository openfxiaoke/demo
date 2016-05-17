package com.facishare.open.demo.beans.args;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.MoreObjects;

public class TextMsgArg extends BaseArg {

    private static final long serialVersionUID = 6772454448304965814L;

    private List<String> toUser;

    private String msgType;

    private Text text;

    public List<String> getToUser() {
        return toUser;
    }

    public void setToUser(List<String> toUser) {
        this.toUser = toUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("corpAccessToken", corpAccessToken)
                .add("corpId", corpId)
                .add("toUser", toUser)
                .add("msgType", msgType)
                .add("text", text)
                .toString();
    }

    public class Text implements Serializable {

        private static final long serialVersionUID = -6344424822430136026L;

        private String content;

        public Text() {}

        public Text(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("content", content)
                    .toString();
        }

    }

}
