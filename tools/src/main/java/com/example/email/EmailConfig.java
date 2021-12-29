package com.example.email;

/**
 * @author dorra
 * @date 2021/12/29 9:57
 * @description 邮件配置
 */
public interface EmailConfig {
    /**
     * 是否开启邮件通知
     *
     * @return
     */
    boolean isOpen();

    /**
     * 发件人邮箱
     *
     * @return
     */
    String getSenderAccount();

    /**
     * 发件人名称
     *
     * @return
     */
    String getSenderPersonal();

    /**
     * 临时授权码-用户可随时关闭
     *
     * @return
     */
    String getSenderToken();

    /**
     * 收件人邮箱
     *
     * @return
     */
    String getReceiverAccount();

    /**
     * 收件人名称
     *
     * @return
     */
    String getReceiverPersonal();
}
