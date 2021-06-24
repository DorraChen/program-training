package com.example;

import com.example.imgparse.JsoupParseHtml;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dorra
 * @date 2021/5/12 15:04
 * @description
 */
public class ImageTest {
    @Test
    public void test() {
        // 创建一个正则表达式模式，用以匹配一个单词（\b=单词边界）
        String patt = "\\bfavor\\b";
        // 用于测试的输入字符串
        String input = "Do me a favor? Fetch my favorites.AAA favor BBB";
        System.out.println("Input:" + input);
        // 从正则表达式实例中运行方法并查看其如何运行
        Pattern r = Pattern.compile(patt);
        Matcher m = r.matcher(input);
        System.out.println("ReplaceAll:" + m.replaceAll("favour"));
        // appendReplacement方法
        m.reset();
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            // 将匹配之前的字符串复制到sb,再将匹配结果替换为："favour"，并追加到sb
            m.appendReplacement(sb, "favour");
        }
        System.out.println(sb.toString());
        m.appendTail(sb);
        System.out.println(sb.toString());
    }

    @Test
    public void testImage() throws IOException {
        File image = new File("D:\\WorkSpace\\imgtest\\test\\1620801022423.jpeg");
        File newImage = new File("D:\\WorkSpace\\imgtest\\test\\1620801340051.jpeg");

        // 编码为 Base64 字符串
        byte[] bytes = FileUtils.readFileToByteArray(image);
        String base64 = new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
        System.out.println(base64);
        // Base64 保存为图片
        bytes = Base64.getDecoder().decode(base64);
        FileUtils.writeByteArrayToFile(newImage, bytes);
    }

    @Test
    public void parseBase64FromHtmlTest() throws IOException {
        System.out.println(ImageTest.class.getResource("test.html").getFile());
        String path = ImageTest.class.getResource("test.html").getFile();
        File file = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        String str = null;
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader in = new BufferedReader(read);
            while ((str = in.readLine()) != null) {
                System.out.println("读取文件内容为:\n" + str);
                stringBuilder.append(str);
            }
        } else {
            System.out.println("找不到文件");
        }
        String html = stringBuilder.toString();
        if (StringUtils.isBlank(html)) {
            System.out.println("文件内容为空");
            return;
        }
        String result = JsoupParseHtml.parseBase64FromHtml(html);
        System.out.println("===================================");
        System.out.println(html);
        System.out.println("===================================");
        System.out.println(result);
    }
}
