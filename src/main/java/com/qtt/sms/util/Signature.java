package com.qtt.sms.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * User: calf
 */
public class Signature {

    public static String getSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        Object key = null;
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                if (entry.getKey().equals("key")) {
                    key = entry.getValue();
                } else {
                    list.add(entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            if (i == size - 1){
                sb.append(arrayToSort[i]);
            } else {
                sb.append(arrayToSort[i]).append("&");
            }
        }
        if (key != null) {
            sb.append("&key=" + key );
        }
        String result = sb.toString();
        //System.out.println(" result : " + result);
        result = MD5.MD5Encode(result);
        return result;
    }

    public static boolean checkIsSignValidFromResponseString(String accid, String timestamp, String key, String sign) throws ParserConfigurationException, IOException, SAXException {

        if(sign == "" || sign == null){
            return false;
        }
        String signForAPIResponse = MD5.MD5Encode(accid + key + timestamp);

        if(!signForAPIResponse.equals(sign)){
            return false;
        }
        return true;
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(Map<String,Object> map) throws ParserConfigurationException, IOException, SAXException {

        String signFromAPIResponse = map.get("sign").toString();
        if(signFromAPIResponse=="" || signFromAPIResponse == null){
            return false;
        }
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = Signature.getSign(map);

        if(!signForAPIResponse.equals(signFromAPIResponse)){
            return false;
        }
        return true;
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(Map<String,Object> map, String key) throws ParserConfigurationException, IOException, SAXException {

        String signFromAPIResponse = map.get("sign").toString();
        //System.out.println("sign from :" + signFromAPIResponse);
        if(signFromAPIResponse=="" || signFromAPIResponse == null){
            return false;
        }
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        map.put("key", key);
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = Signature.getSign(map);

        //System.out.println("sign for :" + signForAPIResponse);
        if(!signForAPIResponse.equals(signFromAPIResponse)){
            return false;
        }
        return true;
    }
    
    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
