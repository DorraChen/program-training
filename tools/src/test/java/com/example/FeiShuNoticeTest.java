package com.example;

import com.example.feishu.niotice.enums.MsgType;
import com.example.feishu.niotice.model.FeiShuMessageResult;
import com.example.feishu.niotice.model.FeiShuRichTextReq;
import com.example.feishu.niotice.model.FeiShuTextReq;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author clz
 * @date 2022/4/12 11:31
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@Slf4j
public class FeiShuNoticeTest {
    private Gson gson = new Gson();

    /**
     * 测试机器人-改成你自定义机器人的webhook 地址和签名校验
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
     * 发送消息 content 类型为 文本 text
     */
    @Test
    public void whenTextContent_thenFeiShuDIYBotNotice() {
        try {
            OkHttpClient client = new OkHttpClient();
            FeiShuTextReq feiShuTextReq = new FeiShuTextReq();
            feiShuTextReq.setMsgType(MsgType.TEXT.getType());
            feiShuTextReq.setContent(FeiShuTextReq.Content.text("文本/富文本"));
            Long timestamp = Instant.now().getEpochSecond();
            String sign = genSign(SECRET, timestamp);
            log.info("sign:{}", sign);
            log.info("timestamp:{}", timestamp);
            feiShuTextReq.setSign(sign);
            feiShuTextReq.setTimestamp(timestamp.toString());
            log.info("feiShuTextReq:{}", feiShuTextReq.toString());
            Request request = new Request.Builder()
                    .url(WEBHOOK)
                    .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), feiShuTextReq.toString()))
                    .build();
            ResponseBody body = client.newCall(request).execute().body();
            if (body != null) {
                final String stringResult = body.string();
                log.info(stringResult);
                final FeiShuMessageResult result = FeiShuMessageResult.valueOf(stringResult);
                log.error("飞书群机器人消息发送结果: code:{}, msg:{}", result.getCode(), result.getMsg());
                Assert.assertTrue(result.isSuccess());
            } else {
                log.error("飞书群机器人消息发送失败！请求没有返回内容");
            }
        } catch (Exception e) {
            log.error("飞书群机器人消息发送失败！" + e.getMessage());
        }
    }

    /**
     * 发送消息 content 类型为 富文本 post
     */
    @Test
    public void whenRichTextContent_thenFeiShuDIYBotNotice() {
        try {
            OkHttpClient client = new OkHttpClient();
            FeiShuRichTextReq feiShuRichTextReq = new FeiShuRichTextReq();
            FeiShuRichTextReq.Body reqBody = new FeiShuRichTextReq.Body();
            reqBody.setTitle("specialStreetList");
            reqBody.setContent(new ArrayList<>());

            FeiShuRichTextReq.BodyContent bodyContent1 = FeiShuRichTextReq.BodyContent.text("湖北省荆门市京山市永兴街道", false);
            List<FeiShuRichTextReq.BodyContent> bodyContentList1 = new ArrayList<>();
            bodyContentList1.add(bodyContent1);
            reqBody.getContent().add(bodyContentList1);

            List<FeiShuRichTextReq.BodyContent> bodyContentList2 = new ArrayList<>();
            FeiShuRichTextReq.BodyContent bodyContent2 = FeiShuRichTextReq.BodyContent.text("湖北省荆门市京山市新市街道", false);
            bodyContentList2.add(bodyContent2);
            reqBody.getContent().add(bodyContentList2);

            List<FeiShuRichTextReq.BodyContent> bodyContentList3 = new ArrayList<>();
            FeiShuRichTextReq.BodyContent bodyContent3 = FeiShuRichTextReq.BodyContent.a("高德地图参考链接",
                    "https://lbs.amap.com/demo/javascript-api/example/district-search/city-drop-down-list/");
            bodyContentList3.add(bodyContent3);
            reqBody.getContent().add(bodyContentList3);

            feiShuRichTextReq.setMsgType(MsgType.POST.getType());
            feiShuRichTextReq.setContent(FeiShuRichTextReq.Content.zhCn(reqBody));

            Long timestamp = Instant.now().getEpochSecond();
            String sign = genSign(SECRET, timestamp);
            log.info("sign:{}", sign);
            log.info("timestamp:{}", timestamp);
            feiShuRichTextReq.setSign(sign);
            feiShuRichTextReq.setTimestamp(timestamp.toString());
            log.info("feiShuRichTextReq:{}", feiShuRichTextReq.toString());
            Request request = new Request.Builder()
                    .url(WEBHOOK)
                    .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), feiShuRichTextReq.toString()))
                    .build();
            ResponseBody body = client.newCall(request).execute().body();
            if (body != null) {
                final String stringResult = body.string();
                log.info(stringResult);
                final FeiShuMessageResult result = FeiShuMessageResult.valueOf(stringResult);
                log.error("飞书群机器人消息发送结果: {}", gson.toJson(result));
                Assert.assertTrue(result.isSuccess());
            } else {
                log.error("飞书群机器人消息发送失败！请求没有返回内容");
            }
        } catch (Exception e) {
            log.error("飞书群机器人消息发送失败！" + e.getMessage());
        }
    }
}
