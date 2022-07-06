package com.example.asynchronous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author dorra
 * @date 2022/2/22 9:43
 * @description 转账服务的实现
 */
@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private AccountService accountService;

    /**
     * 转账
     *
     * @param fromAccount 转出账户
     * @param toAccount   转入账户
     * @param amount      转账金额，单位分
     * @return CompletableFuture
     */
    @Override
    public CompletableFuture<Void> transfer(String fromAccount, String toAccount, int amount) {
        // 异步调用add方法从fromAccount扣减相应金额
        return accountService.add(fromAccount, -1 * amount)
                // 然后调用add方法给toAccount增加相应金额
                .thenCompose(v -> accountService.add(toAccount, amount));
    }
}