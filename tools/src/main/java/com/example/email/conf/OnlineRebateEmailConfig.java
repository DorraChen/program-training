package com.example.email.conf;

import com.example.email.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dorra
 * @date 2021/12/28 15:17
 * @description 返利邮件模块配置
 */
@Component
public class OnlineRebateEmailConfig implements EmailConfig {
    @Autowired
    private OnlineRebateEmailProperties onlineRebateEmailProperties;

    /**
     * 是否开启邮件通知
     *
     * @return
     */
    @Override
    public boolean isOpen() {
        return onlineRebateEmailProperties.isOpen();
    }

    /**
     * 发件人邮箱
     *
     * @return
     */
    @Override
    public String getSenderAccount() {
        return onlineRebateEmailProperties.getSenderAccount();
    }

    /**
     * 发件人名称
     *
     * @return
     */
    @Override
    public String getSenderPersonal() {
        return onlineRebateEmailProperties.getSenderPersonal();
    }

    /**
     * 临时授权码-用户可随时关闭
     *
     * @return
     */
    @Override
    public String getSenderToken() {
        return onlineRebateEmailProperties.getSenderToken();
    }

    /**
     * 收件人邮箱
     *
     * @return
     */
    @Override
    public String getReceiverAccount() {
        return onlineRebateEmailProperties.getReceiverAccount();
    }

    /**
     * 收件人名称
     *
     * @return
     */
    @Override
    public String getReceiverPersonal() {
        return onlineRebateEmailProperties.getReceiverPersonal();
    }
}
