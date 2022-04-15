package com.example.feishu.niotice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dorra
 * @date 2022/4/12 15:58
 * @description 富文本 post 消息实体类
 */
@Data
@Slf4j
public class FeiShuRichTextReq {

    @JsonProperty("sign")
    private String sign;
    @JsonProperty("timestamp")
    private String timestamp;

    private Content content;

    /**
     * 消息类型 包括：text、post、image、file、audio、media、sticker、interactive、share_chat、share_user等
     * 参考: https://open.feishu.cn/document/uAjLw4CM/ukTMukTMukTM/im-v1/message/create_json
     * 这里使用的是富文本
     */
    @JsonProperty("msg_type")
    private String msgType;

    @Data
    public static class Content {

        @JsonProperty("post")
        private Map<String, Body> post;

        @JsonProperty("text")
        private String text;

        public static Content zhCn(Body body) {
            final Content content = new Content();
            content.post = new HashMap<>(16);
            content.post.put("zh_cn", body);
            return content;
        }

        public static Content text(String text) {
            final Content content = new Content();
            content.text = text;
            return content;
        }
    }

    @Data
    public static class Body {

        @JsonProperty("title")
        private String title;

        private List<List<BodyContent>> content = new ArrayList<>();
    }

    @Data
    public static class BodyContent {

        @JsonProperty("tag")
        private String tag;

        /**
         * 显示的默认的文本内容，如果设置了 i18n 内容，会优先显示 i18n 里面对应的语种内容
         */
        @JsonProperty("text")
        private String text;

        /**
         * 表示是不是 unescape 解码，默认为 false ，不用可以不填
         */
        @JsonProperty("un_escape")
        private Boolean unEscape;

        /**
         * 默认的链接地址
         */
        @JsonProperty("href")
        private String href;

        /**
         * open_id 或者 user_id
         */
        @JsonProperty("user_id")
        private String userId;

        /**
         * 图片的唯一标识，可以通过图片上传接口获得
         */
        @JsonProperty("image_key")
        private String imageKey;

        /**
         * 图片的高
         */
        @JsonProperty("width")
        private Integer width;

        /**
         * 图片的宽
         */
        @JsonProperty("height")
        private Integer height;

        public static BodyContent text(String text, boolean unEscape) {
            final BodyContent bodyContent = new BodyContent();
            bodyContent.tag = "text";
            bodyContent.text = text;
            bodyContent.unEscape = unEscape;
            return bodyContent;
        }

        public static BodyContent a(String text, String href) {
            final BodyContent bodyContent = new BodyContent();
            bodyContent.tag = "a";
            bodyContent.text = text;
            bodyContent.href = href;
            return bodyContent;
        }

        public static BodyContent at(String userId) {
            final BodyContent bodyContent = new BodyContent();
            bodyContent.tag = "at";
            bodyContent.userId = userId;
            return bodyContent;
        }

        public static BodyContent image(String imageKey, Integer width, Integer height) {
            final BodyContent bodyContent = new BodyContent();
            bodyContent.tag = "image";
            bodyContent.imageKey = imageKey;
            bodyContent.width = width;
            bodyContent.height = height;
            return bodyContent;
        }
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException processingException) {
            log.error("objectMapper.writeValueAsString(FeiShuRichTextReq)出错", processingException);
            return "";
        }
    }
}
