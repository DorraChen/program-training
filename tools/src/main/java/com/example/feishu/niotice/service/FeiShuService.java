package com.example.feishu.niotice.service;

import com.example.feishu.niotice.enums.MsgType;
import com.example.feishu.niotice.exception.FeiShuException;
import com.example.feishu.niotice.model.FeiShuMessageResult;
import com.example.feishu.niotice.model.FeiShuRichTextReq;
import com.example.feishu.niotice.model.FeiShuTextReq;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

/**
 * @author dorra
 * @date 2022/4/13 17:30
 * @description 飞书服务
 */
@Service
@Slf4j
public class FeiShuService {

    private Gson gson = new Gson();

    /**
     * 改成你自定义机器人的webhook 地址和签名校验
     */
    private static final String SECRET = "...";
    private static final String WEBHOOK = "https://open.feishu.cn/open-apis/bot/v2/hook/XXXX";


    /**
     * 计算飞书群机器人的api的url
     *
     * @param secret    飞书群机器人的密钥
     * @param timestamp 飞书群机器人的时间戳
     * @return api的url
     */
    private static String genSign(String secret, Long timestamp) throws NoSuchAlgorithmException, InvalidKeyException {
        //把timestamp+"\n"+密钥当做签名字符串
        String stringToSign = timestamp + "\n" + secret;

        //使用HmacSHA256算法计算签名
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(stringToSign.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(new byte[]{});
        return new String(Base64.encodeBase64(signData));
    }

    /**
     * 发送消息(消息类型为文本)
     *
     * @param feiShuTextReq 文本消息
     */
    public void sendFeiShuTextMessage(FeiShuTextReq feiShuTextReq) {
        try {
            Long timestamp = Instant.now().getEpochSecond();
            String sign = genSign(SECRET, timestamp);
            feiShuTextReq.setSign(sign);
            feiShuTextReq.setTimestamp(timestamp.toString());
            feiShuTextReq.setMsgType(MsgType.TEXT.getType());
            feiShuDIYBotNotice(feiShuTextReq.toString());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("飞书服务发送文本消息失败:", e);
        } catch (IOException e) {
            log.error("飞书服务发送文本消息失败:", e);
        } catch (FeiShuException e) {
            throw new FeiShuException("飞书服务发送文本消息失败:" + e.getMessage());
        }
    }

    /**
     * 发送消息(消息类型为富文本)
     *
     * @param feiShuRichTextReq 富文本消息
     */
    public void sendFeiShuRichTextMessage(FeiShuRichTextReq feiShuRichTextReq) {
        try {
            Long timestamp = Instant.now().getEpochSecond();
            String sign = genSign(SECRET, timestamp);
            feiShuRichTextReq.setSign(sign);
            feiShuRichTextReq.setTimestamp(timestamp.toString());
            feiShuRichTextReq.setMsgType(MsgType.POST.getType());
            feiShuDIYBotNotice(feiShuRichTextReq.toString());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("飞书服务发送文本消息失败:", e);
        } catch (IOException e) {
            log.error("飞书服务发送文本消息失败:", e);
        } catch (FeiShuException e) {
            throw new FeiShuException("飞书服务发送文本消息失败:" + e.getMessage());
        }
    }

    /**
     * 发送消息
     *
     * @param message 消息
     */
    private void feiShuDIYBotNotice(String message) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(WEBHOOK)
                .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), message))
                .build();
        ResponseBody body = client.newCall(request).execute().body();
        if (body != null) {
            final String stringResult = body.string();
            log.info(stringResult);
            final FeiShuMessageResult result = FeiShuMessageResult.valueOf(stringResult);
            log.error("飞书群机器人消息发送结果: {}", gson.toJson(result));
            if (!result.isSuccess()) {
                throw new FeiShuException(result.getMsg());
            }
        } else {
            log.error("飞书群机器人消息发送失败!请求没有返回内容");
        }
    }
}
