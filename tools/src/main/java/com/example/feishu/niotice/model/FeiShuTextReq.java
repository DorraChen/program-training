package com.example.feishu.niotice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @author dorra
 * @date 2022/4/12 15:58
 * @description 文本 text 消息实体类
 */
@Data
@Slf4j
public class FeiShuTextReq {

    @JsonProperty("sign")
    private String sign;
    @JsonProperty("timestamp")
    private String timestamp;
    private Content content;
    /**
     * 消息类型 包括：text、post、image、file、audio、media、sticker、interactive、share_chat、share_user等
     * 参考: https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/im-v1/message/create_json
     */
    @JsonProperty("msg_type")
    private String msgType;

    @Data
    public static class Content {
        @JsonProperty("text")
        private String text;

        public static Content text(String text) {
            Content content = new Content();
            content.setText(text);
            return content;
        }
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException processingException) {
            log.error("objectMapper.writeValueAsString(FeiShuTextReq)出错", processingException);
            return "";
        }
    }
}
