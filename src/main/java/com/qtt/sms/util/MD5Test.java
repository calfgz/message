package com.qtt.sms.util;

import java.util.HashMap;
import java.util.Map;

public class MD5Test {
	public static void main(String[] args) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("accid", "c287bd645f5e182a0f25db8a7a9b2b9c");
    	map.put("content", "时光流逝、岁月静好，51qtt生生世世2222");
    	map.put("phones", "18621666529");
    	map.put("key", "i41dairr1v3h7yab8ejzdrc90oie1oz7");
    	System.out.println(Signature.getSign(map));
        String result = MD5.MD5Encode("accid=c287bd645f5e182a0f25db8a7a9b2b9c&content=时光流逝、岁月静好，51qtt生生世世2222&phones=18621666529&key=i41dairr1v3h7yab8ejzdrc90oie1oz7");
        System.out.println(result);
	}
}
