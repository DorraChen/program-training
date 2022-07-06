package com.example;

import com.example.asynchronous.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

/**
 * @author dorra
 * @date 2022/2/22 9:55
 * @description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class AsynchronousTest {
    @Autowired
    private Client client;

    @Test
    public void whenSync_thenTransfer() throws ExecutionException, InterruptedException {
        client.syncInvoke("1000", "1001", 100);
    }

    @Test
    public void whenAsync_thenTransfer() {
        client.asyncInvoke("1000", "1001", 100);
    }
}
