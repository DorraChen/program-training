package com.example.email.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dorra
 * @date 2021/12/28 15:17
 * @description
 */
@Component
@ConfigurationProperties(prefix = "onlinerebate-email")
public class OnlineRebateEmailConfig {
    /**
     * 是否开启邮件通知
     */
    public boolean open;
    /**
     * 发件人邮箱
     */
    public String senderAccount;
    /**
     * 临时授权码-用户可随时关闭
     */
    public String senderToken;
    /**
     * 发件人邮箱的smtp服务器地址
     */
    public String smtpHost;
    /**
     * 收件人邮箱
     */
    public String receiverAccount;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getSenderToken() {
        return senderToken;
    }

    public void setSenderToken(String senderToken) {
        this.senderToken = senderToken;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }
}
