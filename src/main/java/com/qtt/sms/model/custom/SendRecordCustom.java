package com.qtt.sms.model.custom;

import com.qtt.sms.model.SendRecord;

public class SendRecordCustom extends SendRecord {
    //queryStatus; //0：未获取，1：已获取
    public String getQueryStatusDesc() {
        if (this.getQueryStatus() == 0) {
            return "未查询";
        } else {
            return "已查询";
        }
    }
    //respStatus;  //回执状态 -1:未获取, 0:成功, 1:失败, 2:未知
    public String getRespStatusDesc() {
        String result = "";
        switch (this.getRespStatus()) {
        case -1: result = "未获取"; break;
        case 0: result = "成功"; break;
        case 1: result = "失败"; break;
        case 2: result = "未知"; break;
        default: result = "未知"; break;
        }
        return result;
    }
}
