package com.example.groupby.webrecord;

import lombok.Data;

/**
 * @author dorra
 * @date 2022/3/29 17:00
 * @description
 */
@Data
public class WebRecord {
    private String fiveMinuteWindow;
    private String cdn;
    private String isp;
    private String resultCode;
    private String txnTime;
    private int reqBytes;

    public WebRecord(String fiveMinuteWindow, String cdn, String isp, String resultCode, String txnTime, int reqBytes) {
        this.fiveMinuteWindow = fiveMinuteWindow;
        this.cdn = cdn;
        this.isp = isp;
        this.resultCode = resultCode;
        this.txnTime = txnTime;
        this.reqBytes = reqBytes;
    }
}
