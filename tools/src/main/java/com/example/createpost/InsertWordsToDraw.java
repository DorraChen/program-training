package com.example.createpost;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dorra
 * @date 2024/1/18 15:56
 * @description 将文字插入图片中
 */
@Slf4j
public class InsertWordsToDraw {
    /**
     * 通过变量将文本分割
     * 例: 入参text = "累计使用了{0}积分，为您节省了{1}元，打败了平台99.99%的用户"
     * amounts = ["518,643,326", "5,186,433.26"]
     * 返回: ["累计使用了", "518,643,326", "积分，为您节省了", "5,186,433.26", "元，打败了平台99.99%的用户"]
     *
     * @param text
     * @param amounts
     * @return
     */
    public static List<String> splitTextByAmount(String text, List<String> amounts) {
//        String text = "累计使用了 {0} 积分，为您节省了 {1}";
//        List<String> amounts = Arrays.asList("518,643,326", "5,186,433.26");

        // 构建正则表达式，使用捕获组
        String patternString = "\\{(\\d)}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        List<String> parts = new ArrayList<>();

        // 使用find方法查找匹配
        int lastEnd = 0;
        while (matcher.find()) {
            String substring = text.substring(lastEnd, matcher.start());
            // 输出分割前的文本段落
            log.info("文本:{}", substring);
            parts.add(substring);
            // 获取占位符的索引
            int placeholderIndex = Integer.parseInt(matcher.group(1));
            String amount = amounts.get(placeholderIndex);
            // 输出金额信息
            log.info("变量:{}", amount);
            parts.add(amount);
            // 更新lastEnd
            lastEnd = matcher.end();
        }
        String lastString = text.substring(lastEnd);
        // 输出最后一个分割后的文本段落
        if (StringUtils.isNotBlank(lastString)) {
            System.out.println(lastString);
            parts.add(lastString);
        }
        return parts;
    }

    /**
     * 列出系统所支持的字体
     */
    public static void listAllFonts() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        for (int i = 0; i < fontNames.length; i++) {
            System.out.println(fontNames[i]);
        }
    }


    public static void main(String[] args) {

        draw("D:\\test\\yearbill1\\背景图.png", "D:\\test\\yearbill_result1\\运行结果.png",
                "您今年一共运动 330 天，累计一年跑步 11,688.67 公里，超越了全球98%的用户",
                Arrays.asList("330", "11,688.67"));
    }

    private static void draw(String fileName, String finallyFile, String text, List<String> amounts) {
        try (InputStream inputStream = new FileInputStream(fileName)) {
            // 创建图片
            BufferedImage image = ImageIO.read(inputStream);
            Graphics2D g2d = image.createGraphics();
            // 设置字体和颜色(需要看系统是否支持该字体, 不支持要更新字体库)
            g2d.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20 * 3));
            g2d.setColor(new Color(0xFFFFFF));

            // 在图片上绘制文字(之所以*3是因为图片选择的是UED提供的3倍像素的)
            g2d.drawString("2023年年度总结", 96 * 3, 75 * 3);


            g2d.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 30 * 3));
            g2d.setColor(new Color(0xFFFFFF));
            g2d.drawString("酷跑达人", 110 * 3, 250 * 3);

            // 设置字体
            Font normalFont = new Font("Microsoft YaHei UI", Font.PLAIN, 16 * 3);
            Font boldFont = new Font("Microsoft YaHei UI", Font.BOLD, 26 * 3);

            // 设置绘制位置
            int x = 28 * 3;
            int y = 398 * 3;

            // 设置换行宽度
            int wrapWidth = 281 * 3;

            g2d.setFont(normalFont);
            // 在图片上绘制文字
            g2d.drawString("在2023年里", x, y);

            // 创建AttributedString
            AttributedString attributedString = parseHtmlText(text, amounts, normalFont, boldFont);

            // 设置行高
            int lineHeight = 32 * 3;
            // 绘制文本
            drawWrappedText(g2d, attributedString, x, y + 28 * 3, wrapWidth, lineHeight);

            // 保存图片
            try {
                ImageIO.write(image, "PNG", new File(finallyFile));
                System.out.println("Image saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 关闭Graphics2D
            g2d.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 需要自动换行和设置特殊字符串的文本(特殊字符串不跨行)
     *
     * @param text
     * @param amounts
     * @param normalFont
     * @param boldFont
     * @return
     */
    private static AttributedString parseHtmlText(String text, List<String> amounts, Font normalFont, Font boldFont) {
        // 移除HTML标签
        String plainText = text.replaceAll("<[^>]+>", "");

        // 创建AttributedString
        AttributedString attributedString = new AttributedString(plainText);

        // 设置普通文本字体，颜色
        attributedString.addAttribute(TextAttribute.FONT, normalFont);
        attributedString.addAttribute(TextAttribute.FOREGROUND, new Color(0xFFFFFF));
        if (!CollectionUtils.isEmpty(amounts)) {
            // 设置特殊字符串的字体，颜色
            for (String amount : amounts) {
                int index1 = plainText.indexOf(amount);
                if (index1 >= 0) {
                    attributedString.addAttribute(TextAttribute.FONT, boldFont, index1, index1 + amount.length());
                    attributedString.addAttribute(TextAttribute.FOREGROUND, new Color(0xFFAD72), index1, index1 + amount.length());
                }
            }
        }

        return attributedString;
    }

    private static void drawWrappedText(Graphics2D g2d, AttributedString attributedString, int x, int y,
                                        int wrapWidth, int lineHeight) {
        // 获取有关字体渲染上下文的信息
        FontRenderContext frc = g2d.getFontRenderContext();
        // 获取AttributedString中字符的迭代器
        AttributedCharacterIterator iterator = attributedString.getIterator();
        // 创建一个LineBreakMeasurer以测量并断开文本
        LineBreakMeasurer measurer = new LineBreakMeasurer(iterator, frc);
        float drawPosY = y;
        // 循环遍历文本, 为每一行计算布局并在指定位置绘制
        while (measurer.getPosition() < iterator.getEndIndex()) {
            TextLayout layout = measurer.nextLayout(wrapWidth);
            // 计算偏移量,使文本在指定行高中居中的偏移量
//            float ascentOffset = Math.abs(lineHeight - layout.getAscent() - layout.getDescent()) / 2;
            // 16.324219f是各种调试和计算出来的, 如果字号或者行距什么的有所调整, 使用上一行这个代码来找这个偏移量
            // 因为特殊字符金额这些字号更大,有时候算出偏移量会有所改变,算出来的是3.多,画出来的图文字有点偏上了,所以取的这个值
            float ascentOffset = 16.324219f;
            // 计算绘制文本的垂直位置
            float v = drawPosY + ascentOffset;
            // 绘制文本
            layout.draw(g2d, x, v);
            System.out.println("ascentOffset: " + ascentOffset);
            System.out.println("ascent: " + layout.getAscent());
            System.out.println("descent: " + layout.getDescent());
            System.out.println("v: " + v);

            // 更新绘制位置
            drawPosY += lineHeight;
            System.out.println("drawPosY" + drawPosY);
            System.out.println("==================");
        }
    }
}
