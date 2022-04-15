package com.example.feishu.niotice.api;

import java.util.List;

/**
 * @author dorra
 * @date 2022/4/14 11:02
 * @description
 */
public interface FeiShuApi {
    /**
     * 发送飞书消息
     *
     * @param type    消息类型:包括text、post、image、file、audio、media、sticker、interactive、share_chat、share_user等
     * @param title   标题
     * @param content 消息内容 list 为每行内容
     * @param url     链接
     */
    void sendFeiShuMessage(String type, String title, List<String> content, String url);
}
