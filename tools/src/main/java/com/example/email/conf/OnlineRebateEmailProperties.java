package com.example.email.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dorra
 * @date 2021/12/29 10:03
 * @description 返利邮件模块配置
 */
@Component
@ConfigurationProperties(prefix = "onlinerebate-email")
public class OnlineRebateEmailProperties {
    /**
     * 是否开启邮件通知
     */
    public boolean open = true;
    /**
     * 发件人邮箱
     */
    public String senderAccount;
    /**
     * 发件人名称
     */
    public String senderPersonal;
    /**
     * 临时授权码-用户可随时关闭
     */
    public String senderToken;
    /**
     * 收件人邮箱
     */
    public String receiverAccount;
    /**
     * 收件人名称
     */
    public String receiverPersonal;

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

    public String getSenderPersonal() {
        return senderPersonal;
    }

    public void setSenderPersonal(String senderPersonal) {
        this.senderPersonal = senderPersonal;
    }

    public String getSenderToken() {
        return senderToken;
    }

    public void setSenderToken(String senderToken) {
        this.senderToken = senderToken;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getReceiverPersonal() {
        return receiverPersonal;
    }

    public void setReceiverPersonal(String receiverPersonal) {
        this.receiverPersonal = receiverPersonal;
    }
}
