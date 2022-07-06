package com.example.asynchronous;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author dorra
 * @date 2022/2/22 10:44
 * @description 账户服务的实现
 */
@Service
public class AccountServiceImpl implements AccountService {
    private static final Map<String, Integer> accountMap = new HashMap<>(16);

    static {
        accountMap.put("1000", 100);
        accountMap.put("1001", 100);
    }

    /**
     * 变更账户金额
     *
     * @param account 账户ID
     * @param amount  增加的金额，负值为减少
     * @return CompletableFuture
     */
    @Override
    public CompletableFuture<Void> add(String account, int amount) {

        return null;
    }
}
