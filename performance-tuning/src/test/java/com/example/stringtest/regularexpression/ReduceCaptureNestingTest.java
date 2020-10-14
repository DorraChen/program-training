package string.regularexpression;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author clz
 * @data 2020/10/9 15:47
 * @description 减少捕获嵌套
 */
public class ReduceCaptureNestingTest {
    @Test
    public void reduceNestingV1() {
        String text = "<input high=\"20\" weight=\"70\">test</input>";
        String reg = "(<input.*?>)(.*?)(</input>)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(m.group(0));// 整个匹配到的内容
            System.out.println(m.group(1));//(<input.*?>)
            System.out.println(m.group(2));//(.*?)
            System.out.println(m.group(3));//(</input>)
        }
    }

    @Test
    public void reduceNestingV2() {
        String text = "<input high=\"20\" weight=\"70\">test</input>";
        String reg = "(?:<input.*?>)(.*?)(?:</input>)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(m.group(0));// 整个匹配到的内容
            System.out.println(m.group(1));//(.*?)
        }
    }
}
