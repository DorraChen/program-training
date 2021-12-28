package com.example.email.config;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author dorra
 * @date 2021/12/28 15:47
 * @description 发送消息实体
 */
@Setter
@Getter
public class SendMessageModel {
    /**
     * 是否开启邮件通知
     */
    public boolean open;
    /**
     * 发件人邮箱
     */
    public String senderAccount;
    /**
     * 发件人名称
     */
    private String senderPersonal;
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
    /**
     * 收件人名称
     */
    private String receiverPersonal;
    /**
     * 发件时间
     */
    private Date sentDate;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件正文
     */
    private String content;
}
