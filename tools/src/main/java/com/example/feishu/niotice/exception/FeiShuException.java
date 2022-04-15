package com.example.feishu.niotice.exception;

/**
 * @author dorra
 * @date 2022/2/24 15:46
 * @description 飞书相关自定义异常
 */
public class FeiShuException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public FeiShuException(String message) {
        super(message);
    }
}
