package com.example.email;

import com.example.email.conf.OnlineRebateEmailConfig;
import com.example.email.impl.EmailEventSmtpImpl;
import com.example.email.model.SendMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author dorra
 * @date 2021/12/29 10:50
 * @description
 */
@RestController
public class Controller {
    @Autowired
    private OnlineRebateEmailConfig onlineRebateEmailConfig;
    @Autowired
    private EmailEventSmtpImpl emailEventSmtpImpl;
    @PostMapping(value = "testEmail")
    public void test() throws Exception {
        SendMessageModel sendMessageModel = new SendMessageModel();
        sendMessageModel.setSubject("test");
        sendMessageModel.setContent("hahahah");
        sendMessageModel.setSentDate(new Date());
        emailEventSmtpImpl.sendEmail(onlineRebateEmailConfig, sendMessageModel);
    }
}
