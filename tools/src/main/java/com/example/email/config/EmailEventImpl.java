package com.example.email.config;

import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author dorra
 * @date 2021/12/28 15:34
 * @description
 */
@Component
public class EmailEventImpl implements EmailEvent {

    /**
     * 发送邮件
     *
     * @param sendMessageModel 发送实体
     * @throws Exception
     */
    @Override
    public void sendEmail(SendMessageModel sendMessageModel) throws Exception {
        if (!sendMessageModel.isOpen()) {
            return;
        }
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();
        // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.transport.protocol", "smtp");
        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.host", sendMessageModel.getSmtpHost());
        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);

        // 3. 创建一封邮件
        // 3.1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 3.2. From: 发件人
        message.setFrom(new InternetAddress(sendMessageModel.getSenderAccount(), sendMessageModel.getSenderPersonal(), "UTF-8"));

        // 3.3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(sendMessageModel.getReceiverAccount(), sendMessageModel.getReceiverPersonal(), "UTF-8"));

        // 3.4. Subject: 邮件主题
        message.setSubject(sendMessageModel.getSubject(), "UTF-8");

        // 3.5. Content: 邮件正文（可以使用html标签）
        message.setContent(sendMessageModel.getContent(), "text/html;charset=UTF-8");

        // 3.6. 设置发件时间
        message.setSentDate(sendMessageModel.getSentDate());

        // 3.7. 保存设置
        message.saveChanges();

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        transport.connect(sendMessageModel.getSenderAccount(), sendMessageModel.getSenderToken());

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }

//    /**
//     * 创建参数配置, 用于连接邮件服务器的参数配置
//     *
//     * @return
//     */
//    @Override
//    public Properties setProperties() {
//        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
//        Properties props = new Properties();
//        // 使用的协议（JavaMail规范要求）
//        props.setProperty("mail.transport.protocol", "smtp");
//        // 发件人的邮箱的 SMTP 服务器地址
//        props.setProperty("mail.smtp.host", onlineRebateEmailConfig.smtpHost);
//        // 需要请求认证
//        props.setProperty("mail.smtp.auth", "true");
//        return props;
//    }
//
//    /**
//     * 设置发送消息实体
//     *
//     * @return SendMessageModel
//     */
//    @Override
//    public SendMessageModel setSendMessageModel() {
//        return null;
//    }
//
//    /**
//     * 创建会话对象, 用于和邮件服务器交互
//     *
//     * @return Session
//     */
//    @Override
//    public Session setSession() {
//        Session session = Session.getDefaultInstance(setProperties());
//        // 设置为debug模式, 可以查看详细的发送 log
//        session.setDebug(true);
//        return session;
//    }
//
//    /**
//     * 创建一封只包含文本的简单邮件
//     *
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public MimeMessage createMimeMessage() throws Exception {
//        SendMessageModel sendMessageModel = new SendMessageModel();
//        Session session = setSession();
//        // 1. 创建一封邮件
//        MimeMessage message = new MimeMessage(session);
//        // 2. From: 发件人
//        message.setFrom(new InternetAddress(onlineRebateEmailConfig.senderAccount, sendMessageModel.getSenderPersonal(), "UTF-8"));
//        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
//        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(onlineRebateEmailConfig.receiverAccount, sendMessageModel.getSenderPersonal(), "UTF-8"));
//        // 4. Subject: 邮件主题
//        message.setSubject(sendMessageModel.getSubject(), "UTF-8");
//        // 5. Content: 邮件正文
//        message.setContent(sendMessageModel.getContent(), "text/html;charset=UTF-8");
//        // 6. 设置发件时间
//        message.setSentDate(sendMessageModel.getSentDate());
//        // 7. 保存设置
//        message.saveChanges();
//
//        return message;
//    }
}
