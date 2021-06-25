package com.example.imgparse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dorra
 * @date 2021/5/23 20:04
 * @description 解析html
 */
public class JsoupParseHtml {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsoupParseHtml.class);

    /**
     * @param url
     * @return
     */
    public static String parseBase64FromUrl(String url) {
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url), 3000);
        } catch (IOException e) {
            LOGGER.error("Parse url to Document fail! ", e);
        }
        if (null == document) {
            return null;
        }
        return parseDocument(document);
    }

    /**
     * @param html
     * @return
     */
    public static String parseBase64FromHtml(String html) {
        Document document = Jsoup.parse(html, "utf-8");
        return parseDocument(document);
    }

    /**
     * base64 图片的 src 内容
     */
    private static final Pattern BASE64_SRC = Pattern.compile("^data:image/(gif|png|jpeg|jpg|BMP);base64,.*");

    /**
     * @param document
     * @return
     */
    private static String parseDocument(Document document) {
        Elements imgList = document.body().getElementsByTag("img");
        for (Element img : imgList) {
            String src = img.attr("src");
            Matcher matcher = BASE64_SRC.matcher(src);
            if (matcher.matches()) {
                MultipartFile multipartFile = base64ToMultipart(src);
                img.attr("src", uploadMultipartFile(multipartFile));
            }
        }
        return document.getElementsByTag("body").html();
    }

    /**
     * 将 base64 编码转成 MultipartFile
     *
     * @param base64 base64编码
     * @return
     */
    private static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStr = base64.split(",");
            byte[] b = Base64.decodeBase64(baseStr[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64DecodedMultipartFile(b, baseStr[0]);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /**
     * 将图片文件上传至服务器, 并返回图片url(此例子只是将图片存至本地)
     *
     * @param multipartFile 文件
     * @return
     */
    private static String uploadMultipartFile(MultipartFile multipartFile) {
        String fileDictionary = "C:\\Users\\admin\\Pictures\\test\\";
        String fileName = multipartFile.getName();
        File dest = new File(new File(fileDictionary).getAbsolutePath() + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        // 保存文件
        try (FileOutputStream os = new FileOutputStream(fileDictionary + fileName);
             InputStream in = multipartFile.getInputStream()) {
            //定义输出流
            int b = 0;
            while ((b = in.read()) != -1) {
                //读取文件
                os.write(b);
            }
        } catch (IOException e) {
            LOGGER.error("IOException", e);
            return null;
        }
        return fileDictionary + fileName;
    }

    /**
     * 获取不带 - 的 UUID
     *
     * @return
     */
//    private static String getUuid() {
//        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
//    }


    // *******************************正则表达式解析************************************************ //
    /**
     * 匹配字符串中的img标签 正则表达式
     */
    private static final Pattern PATTERN = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");

    /**
     * 匹配图片的 base64 编码 正则表达式
     * <p>
     * base64 编码的 img 标签中 src 可能包含的情况:
     * data:image/gif;base64,     base64编码的gif图片数据
     * data:image/png;base64,     base64编码的png图片数据
     * data:image/jpeg;base64,    base64编码的jpeg图片数据
     * data:image/x-icon;base64,  base64编码的icon图片数据
     * <p>
     */
    private static final Pattern SRC_TEXT = Pattern.compile("(src|SRC)=([\"\'])data:image/(gif|png|jpeg|jpg|BMP);base64,(.*?)([\"\'])");


    /**
     * 获取富文本中 base64 编码
     *
     * @param content
     * @return
     */
    public static List<String> extractImg(String content) {
        // 用来存储获取到的图片的 base64 编码
        List<String> srcList = new ArrayList<>();
        if (StringUtils.isBlank(content)) {
            return srcList;
        }
        // 匹配字符串中的img标签
        Matcher matcher = PATTERN.matcher(content);
        boolean hasPic = matcher.find();
        // 判断是否含有图片
        if (hasPic) {
            // 如果含有图片，那么持续进行查找，直到匹配不到
            while (hasPic) {
                // 获取第2个分组的内容，也就是 (.*?) 匹配到的
                String group = matcher.group(2);
                // 匹配图片的 base64 编码

                Matcher matcher2 = SRC_TEXT.matcher(group);
                if (matcher2.find()) {
                    // 获取第4个分组的内容,把获取到的图片 base64 添加到列表中
                    srcList.add(matcher2.group(4));
                }
                // 判断是否还有img标签
                hasPic = matcher.find();
            }
        }
        return srcList;
    }

}
