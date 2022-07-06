package com.example.asynchronous;

import java.util.concurrent.CompletableFuture;

/**
 * @author dorra
 * @date 2022/2/22 9:39
 * @description 账户服务
 */
public interface AccountService {

    /**
     * 变更账户金额
     *
     * @param account 账户ID
     * @param amount  增加的金额，负值为减少
     * @return CompletableFuture
     */
    CompletableFuture<Void> add(String account, int amount);
}