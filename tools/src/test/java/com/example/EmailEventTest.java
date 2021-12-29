package com.example;

import com.example.email.conf.OnlineRebateEmailConfig;
import com.example.email.impl.EmailEventSmtpImpl;
import com.example.email.model.SendMessageModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author dorra
 * @date 2021/12/28 16:18
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class EmailEventTest {
    @Autowired
    private OnlineRebateEmailConfig onlineRebateEmailConfig;
    @Autowired
    private EmailEventSmtpImpl emailEventSmtpImpl;

    @Test
    public void whenGetConfig_thenSendEmail() throws Exception {
        SendMessageModel sendMessageModel = new SendMessageModel();
        sendMessageModel.setSubject("test");
        sendMessageModel.setContent("hahahah");
        sendMessageModel.setSentDate(new Date());
        emailEventSmtpImpl.sendEmail(onlineRebateEmailConfig, sendMessageModel);
    }
}
