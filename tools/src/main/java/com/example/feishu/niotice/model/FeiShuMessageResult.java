package com.example.feishu.niotice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.IOException;

/**
 * FeiShuMessageResult
 *
 * @author dorra
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FeiShuMessageResult extends FeiShuResult {

    @JsonProperty("data")
    private DataBody data;

    @JsonProperty("StatusCode")
    private Integer statusCode;

    @JsonProperty("StatusMessage")
    private String statusMessage;

    @Data
    public static class DataBody {

        @JsonProperty("message_id")
        private String message_id;

        @JsonProperty("root_id")
        private String root_id;

        @JsonProperty("parent_id")
        private String parent_id;

        @JsonProperty("msg_type")
        private String msg_type;

        @JsonProperty("create_time")
        private String create_time;

        @JsonProperty("update_time")
        private String update_time;

        @JsonProperty("deleted")
        private Boolean deleted;

        @JsonProperty("updated")
        private Boolean updated;

        @JsonProperty("chat_id")
        private String chat_id;
    }

    @Override
    public boolean isSuccess() {
        if (statusCode != null) {
            return FeiShuResult.SUCCESS_CODE.equals(statusCode);
        } else {
            return  super.isSuccess();
        }
    }

    public static FeiShuMessageResult valueOf(String body) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(body, FeiShuMessageResult.class);
    }
}
