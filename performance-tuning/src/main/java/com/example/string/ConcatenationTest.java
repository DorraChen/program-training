package com.example.string;

import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

/**
 * @author dorra
 * @date 2022/4/1 15:54
 * @description Java String Concatenation and Performance
 * 参考:
 * (1)https://venishjoe.net/post/java-string-concatenation-and-performance/
 * (2)https://coolshell.cn/articles/2235.html
 */
public class ConcatenationTest {
    private static final int OUTER_ITERATION = 20;
    private static final int INNER_ITERATION = 50000;

    public static void main(String[] args) {
        String addTestStr = "";
        String concatTestStr = "";
        StringBuffer concatTestSb = null;
        StringBuilder concatTestSbu = null;

        for (int outerIndex = 0; outerIndex <= OUTER_ITERATION; outerIndex++) {
            StopWatch stopWatch = new LoggingStopWatch("StringAddConcat");
            addTestStr = "";
            for (int innerIndex = 0; innerIndex <= INNER_ITERATION; innerIndex++)
                addTestStr += "*";
            stopWatch.stop();
        }

        for (int outerIndex = 0; outerIndex <= OUTER_ITERATION; outerIndex++) {
            StopWatch stopWatch = new LoggingStopWatch("StringConcat");
            concatTestStr = "";
            for (int innerIndex = 0; innerIndex <= INNER_ITERATION; innerIndex++)
                concatTestStr.concat("*");
            stopWatch.stop();
        }

        for (int outerIndex = 0; outerIndex <= OUTER_ITERATION; outerIndex++) {
            StopWatch stopWatch = new LoggingStopWatch("StringBufferConcat");
            concatTestSb = new StringBuffer();
            for (int innerIndex = 0; innerIndex <= INNER_ITERATION; innerIndex++)
                concatTestSb.append("*");
            stopWatch.stop();
        }

        for (int outerIndex = 0; outerIndex <= OUTER_ITERATION; outerIndex++) {
            StopWatch stopWatch = new LoggingStopWatch("StringBuilderConcat");
            concatTestSbu = new StringBuilder();
            for (int innerIndex = 0; innerIndex <= INNER_ITERATION; innerIndex++)
                concatTestSbu.append("*");
            stopWatch.stop();
        }
    }
}
