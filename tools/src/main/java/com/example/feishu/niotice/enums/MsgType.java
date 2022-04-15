package com.example.feishu.niotice.enums;

/**
 * @author dorra
 * @date 2022/4/12 17:17
 * @description 消息类型 包括：text、post、image、file、audio、media、sticker、interactive、share_chat、share_user等
 * 参考: https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/im-v1/message/create_json
 */
public enum MsgType {
    /**
     * text、post、image、file、audio、media
     */
    TEXT("text"),
    POST("post"),
    IMAGE("image"),
    FILE("file"),
    AUDIO("audio"),
    MEDIA("media");

    private String type;

    MsgType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}