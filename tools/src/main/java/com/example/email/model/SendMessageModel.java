package com.example.email.model;

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
