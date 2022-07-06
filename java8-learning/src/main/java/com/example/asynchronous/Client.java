package com.example.asynchronous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author dorra
 * @date 2022/2/22 9:47
 * @description TODO
 */
@Component
public class Client {
    @Autowired
    private TransferService transferService;

    public void syncInvoke(String fromAccount, String toAccount, int amount) throws ExecutionException, InterruptedException {
        // 同步调用
        transferService.transfer(fromAccount, toAccount, amount).get();
        System.out.println("转账完成！");
    }

    public void asyncInvoke(String fromAccount, String toAccount, int amount) {
        // 异步调用
        transferService.transfer(fromAccount, toAccount, amount)
                .thenRun(() -> System.out.println("转账完成！"));
    }
}