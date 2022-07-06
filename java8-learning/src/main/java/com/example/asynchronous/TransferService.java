package com.example.asynchronous;

import java.util.concurrent.CompletableFuture;

/**
 * @author dorra
 * @date 2022/2/22 9:41
 * @description 转账服务
 */
public interface TransferService {

    /**
     * 异步转账服务
     *
     * @param fromAccount 转出账户
     * @param toAccount   转入账户
     * @param amount      转账金额，单位分
     * @return CompletableFuture
     */
    CompletableFuture<Void> transfer(String fromAccount, String toAccount, int amount);
}
