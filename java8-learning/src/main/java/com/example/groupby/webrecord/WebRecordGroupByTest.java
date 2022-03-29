package com.example.groupby.webrecord;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author dorra
 * @date 2022/3/29 17:00
 * @description
 */
@Slf4j
public class WebRecordGroupByTest {
    private static Gson gson = new Gson();
    public static void main(String[] args) {
        List<WebRecord> webRecords = new ArrayList<>();
        webRecords.add(new WebRecord("1", "www.baidu.com", "GET", "200", "1", 1));
        webRecords.add(new WebRecord("1", "www.baidu.com", "GET", "200", "1", 1));
        webRecords.add(new WebRecord("1", "www.baidu.com", "GET", "200", "1", 1));
        webRecords.add(new WebRecord("1", "www.baidu.com", "GET", "200", "1", 1));
        webRecords.add(new WebRecord("2", "www.baidu.com", "GET", "502", "1", 1));


        Function<WebRecord, List<Object>> keyExtractor = wr ->
                Arrays.asList(wr.getFiveMinuteWindow(), wr.getCdn(), wr.getIsp(),
                        wr.getResultCode(), wr.getTxnTime());

        Map<List<Object>, Integer> aggregatedData = webRecords.stream().collect(
                Collectors.groupingBy(keyExtractor, Collectors.summingInt(WebRecord::getReqBytes)));
        log.info("aggregatedData: {}", gson.toJson(aggregatedData));

    }
}
