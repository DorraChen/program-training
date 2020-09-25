package com.example;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author clz
 * @data 2020/9/25 9:28
 * @description 获取中文词组第一个字符的大写拼音首字母, 如 地址:D, 重庆:C
 * 如果需要添加多音词组, 请在 DUOYIN_CIZU 和 DUOYIN_PINYIN 中相应添加.
 */
public class GetFirstLetterUtil {
    /**
     * 多音字词组
     */
    private static final String[] DUOYIN_CIZU = new String[]{"重庆"};
    /**
     * 词组对应的第一个字的大写拼音
     */
    private static final String[] DUOYIN_PINYIN = new String[]{"CHONG"};

    private static final char a = 'a';
    private static final char z = 'z';
    private static final char aA = 'A';
    private static final char zZ = 'Z';

    /**
     * 拼音简拼输出
     *
     * @param chinese 输入字符串
     * @return String 返回第一个字的拼音大写首字母
     */
    public static String getFirstLetterCapital(String chinese) {
        return getFirstLetter(getFirstLetterPinyin(chinese));
    }

    /**
     * 返回一个字符串的第一个字符
     *
     * @param string 字符串
     * @return String 返回第一个字符
     */
    private static String getFirstLetter(String string) {
        if (StringUtils.isNotBlank(string)) {
            return String.valueOf(string.charAt(0));
        }
        return string;
    }

    /**
     * 获取第一个中文汉字拼音 默认输出
     *
     * @param chinese 输入汉字
     * @return String 输出第一个字符的大写拼音
     */
    private static String getFirstLetterPinyin(String chinese) {
        char[] chars = chinese.toCharArray();
        if (chinese != null && !chinese.trim().equalsIgnoreCase("")) {
            String polyphone = polyphone(chinese);
            if (polyphone != null) {
                return polyphone;
            }
            char[] srcChar = chinese.toCharArray();
            String[] temp = new String[1];
            char c = srcChar[0];

            // 是中文或者a-z或者A-Z转换拼音
            if (matchChineseCharacter(c)) {
                try {
                    temp = PinyinHelper.toHanyuPinyinStringArray(chars[0], getDefaultOutputFormat());
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else if (c >= a && c <= z) {
                temp = new String[]{String.valueOf((char) (c - 32))};
            } else if (c >= aA && c <= zZ) {
                temp = new String[]{String.valueOf(srcChar[0])};
            } else {
                temp = new String[]{""};
            }
            return temp[0];
        }
        return "";
    }

    /**
     * 多音字特殊处理
     *
     * @param chinese 字符串
     * @return String 返回多音词组的第一个字的大写拼音
     */
    private static String polyphone(String chinese) {
        List<String> duoYinZiList = Arrays.asList(DUOYIN_CIZU);
        for (int i = 0; i < duoYinZiList.size(); i++) {
            if (chinese.startsWith(duoYinZiList.get(i))) {
                return DUOYIN_PINYIN[i];
            }
        }
        return null;
    }

    /**
     * 判断某个字符是不是中文汉字
     *
     * @param c
     * @return
     */
    private static boolean matchChineseCharacter(char c) {
        String regex = "[\\u4E00-\\u9FA5]+";
        return String.valueOf(c).matches(regex);
    }

    /**
     * Default Format 默认输出格式
     *
     * @return HanyuPinyinOutputFormat define how the Hanyu Pinyin should be outputted
     */
    private static HanyuPinyinOutputFormat getDefaultOutputFormat() {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 大写
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // 没有音调数字
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // u显示
        format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
        return format;
    }

    public static void main(String[] args) {
        System.out.println(getFirstLetterCapital("重庆市"));
        System.out.println(getFirstLetterCapital(""));
        System.out.println(getFirstLetterCapital("哈尔滨"));
        System.out.println(getFirstLetterCapital("安阳市"));
    }
}
