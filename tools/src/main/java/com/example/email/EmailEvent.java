package com.example.email;

import com.example.email.model.SendMessageModel;

import java.util.Properties;

/**
 * @author dorra
 * @date 2021/12/28 15:28
 * @description 接口
 */
public interface EmailEvent {
    /**
     * 创建参数配置, 用于连接邮件服务器的参数配置
     *
     * @return Properties
     */
    Properties createProperties();

    /**
     * 发送邮件
     *
     * @param emailConfig      邮件配置
     * @param sendMessageModel 发送实体
     * @throws Exception
     */
    void sendEmail(EmailConfig emailConfig, SendMessageModel sendMessageModel) throws Exception;
}
