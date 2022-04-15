package com.example.feishu.niotice.api;

import com.example.feishu.niotice.model.FeiShuRichTextReq;
import com.example.feishu.niotice.model.FeiShuTextReq;
import com.example.feishu.niotice.service.FeiShuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dorra
 * @date 2022/4/14 11:06
 * @description
 */
@Component
@Slf4j
public class FeiShuApiImpl implements FeiShuApi {

    @Autowired
    private FeiShuService feiShuService;

    /**
     * 发送飞书消息
     *
     * @param type    消息类型:包括text、post、image、file、audio、media、sticker、interactive、share_chat、share_user等
     * @param title   标题
     * @param content 消息内容 list 为每行内容
     * @param url     链接
     */
    @Override
    public void sendFeiShuMessage(String type, String title, List<String> content, String url) {
        if (CollectionUtils.isEmpty(content)) {
            log.info("消息内容为空,无需发送消息");
            return;
        }
        switch (type) {
            case "text":
                feiShuService.sendFeiShuTextMessage(assembleTextContent(title, content));
                break;
            case "post":
                feiShuService.sendFeiShuRichTextMessage(assemblePostContent(title, content, url));
                break;
            default:
                log.error("方法待开发...");
                break;
        }
    }

    /**
     * 拼装飞书文本消息实体
     *
     * @param title   标题
     * @param content 消息内容 list 为每行内容
     * @return FeiShuTextReq
     */
    private FeiShuTextReq assembleTextContent(String title, List<String> content) {
        List<String> newContent = new ArrayList<>();
        newContent.add(title);
        newContent.addAll(content);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : newContent) {
            stringBuilder.append(s).append("\n");
        }
        FeiShuTextReq feiShuTextReq = new FeiShuTextReq();
        feiShuTextReq.setContent(FeiShuTextReq.Content.text(stringBuilder.toString()));
        return feiShuTextReq;
    }

    /**
     * 拼装飞书富文本消息实体
     *
     * @param title   标题
     * @param content 消息内容 list 为每行内容
     * @param url     链接
     * @return FeiShuRichTextReq
     */
    private FeiShuRichTextReq assemblePostContent(String title, List<String> content, String url) {
        FeiShuRichTextReq feiShuRichTextReq = new FeiShuRichTextReq();
        FeiShuRichTextReq.Body reqBody = new FeiShuRichTextReq.Body();
        reqBody.setTitle(title);
        reqBody.setContent(new ArrayList<>());

        for (String s : content) {
            FeiShuRichTextReq.BodyContent bodyContent = FeiShuRichTextReq.BodyContent.text(s, false);
            reqBody.getContent().add(Arrays.asList(bodyContent));
        }

        if (StringUtils.isNotBlank(url)) {
            FeiShuRichTextReq.BodyContent bodyUrl = FeiShuRichTextReq.BodyContent.a("参考链接", url);
            reqBody.getContent().add(Arrays.asList(bodyUrl));
        }

        feiShuRichTextReq.setContent(FeiShuRichTextReq.Content.zhCn(reqBody));
        return feiShuRichTextReq;
    }
}
