package com.example.email.config;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author dorra
 * @date 2021/12/28 15:28
 * @description
 */
public interface EmailEvent {
//
//    /**
//     * 创建参数配置, 用于连接邮件服务器的参数配置
//     *
//     * @return Properties
//     */
//    Properties setProperties();
//
//    /**
//     * 设置发送消息实体
//     *
//     * @return SendMessageModel
//     */
//    SendMessageModel setSendMessageModel();
//
//    /**
//     * 创建会话对象, 用于和邮件服务器交互
//     *
//     * @return Session
//     */
//    Session setSession();
//
//    /**
//     * 创建一封只包含文本的简单邮件
//     *
//     * @return MimeMessage
//     * @throws Exception
//     */
//    MimeMessage createMimeMessage() throws Exception;

    /**
     * 发送邮件
     *
     * @param sendMessageModel 发送实体
     * @throws Exception
     */
    void sendEmail(SendMessageModel sendMessageModel) throws Exception;
}
