package com.example;

import com.example.email.config.OnlineRebateEmailConfig;
import com.example.email.config.SendMessageModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Test
    public void whenGetConfig_thenSendEmail() {
        SendMessageModel sendMessageModel = new SendMessageModel();

    }
}
