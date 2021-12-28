package com.example.email.config;

import java.util.Date;

/**
 * @author dorra
 * @date 2021/12/28 17:57
 * @description
 */
public final class SendMessageModelBuilder {
    public boolean open;
    public String senderAccount;
    public String senderToken;
    public String smtpHost;
    public String receiverAccount;
    private String senderPersonal;
    private String receiverPersonal;
    private Date sentDate;
    private String subject;
    private String content;

    private SendMessageModelBuilder() {
    }

    public static SendMessageModelBuilder aSendMessageModel() {
        return new SendMessageModelBuilder();
    }

    public SendMessageModelBuilder withOpen(boolean open) {
        this.open = open;
        return this;
    }

    public SendMessageModelBuilder withSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
        return this;
    }

    public SendMessageModelBuilder withSenderPersonal(String senderPersonal) {
        this.senderPersonal = senderPersonal;
        return this;
    }

    public SendMessageModelBuilder withSenderToken(String senderToken) {
        this.senderToken = senderToken;
        return this;
    }

    public SendMessageModelBuilder withSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
        return this;
    }

    public SendMessageModelBuilder withReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
        return this;
    }

    public SendMessageModelBuilder withReceiverPersonal(String receiverPersonal) {
        this.receiverPersonal = receiverPersonal;
        return this;
    }

    public SendMessageModelBuilder withSentDate(Date sentDate) {
        this.sentDate = sentDate;
        return this;
    }

    public SendMessageModelBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public SendMessageModelBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public SendMessageModel build() {
        SendMessageModel sendMessageModel = new SendMessageModel();
        sendMessageModel.setOpen(open);
        sendMessageModel.setSenderAccount(senderAccount);
        sendMessageModel.setSenderPersonal(senderPersonal);
        sendMessageModel.setSenderToken(senderToken);
        sendMessageModel.setSmtpHost(smtpHost);
        sendMessageModel.setReceiverAccount(receiverAccount);
        sendMessageModel.setReceiverPersonal(receiverPersonal);
        sendMessageModel.setSentDate(sentDate);
        sendMessageModel.setSubject(subject);
        sendMessageModel.setContent(content);
        return sendMessageModel;
    }
}
