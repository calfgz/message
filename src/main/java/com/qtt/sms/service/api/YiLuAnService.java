package com.qtt.sms.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.qtt.sms.config.SmsConfig;
import com.qtt.sms.util.HttpsRequest;
import com.qtt.sms.util.T;
import com.qtt.sms.util.XMLParser;

/**
 * @author pc
 * 易路安短信总入口
 */
@Service
public class YiLuAnService {
	
	private Log log = LogFactory.getLog(YiLuAnService.class);
    
    private HttpsRequest httpsRequest;
    
    public YiLuAnService() throws Exception {
        httpsRequest = new HttpsRequest();
    }
    
    /**
     * 发送短信
     * @param phones
     * @param content
     * @return
     * @throws Exception
     */
    public Map<String, Object> send(String phones, String content, Date sendTime) throws Exception {   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userid", SmsConfig.YiLuAn_USER_ID);
        paramMap.put("account", SmsConfig.YiLuAn_ACCOUNT);
        paramMap.put("password", SmsConfig.YiLuAn_PASSWORD);
        paramMap.put("content", content);
        paramMap.put("mobile", phones);
        paramMap.put("action", "send");
        if (sendTime != null) {
        	paramMap.put("sendTime", T.format(sendTime, "yyyy-MM-dd hh:mm:ss"));
        }        
        String apiUrl = SmsConfig.YiLuAn_BASE_URL + "sms.aspx";        
        String contentXml = httpsRequest.sendPost(apiUrl, paramMap);
    	log.debug(contentXml);
    	
    	Map<String, Object> resultMap = XMLParser.getMapFromXML(contentXml);
    	
    	return resultMap;
    }
    
    /**
     * 余额及已发送量查询
     * @return
     * @throws Exception
     */
    public Map<String, Object> overage() throws Exception {
    	Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userid", SmsConfig.YiLuAn_USER_ID);
        paramMap.put("account", SmsConfig.YiLuAn_ACCOUNT);
        paramMap.put("password", SmsConfig.YiLuAn_PASSWORD);
        paramMap.put("action", "overage");
        String apiUrl = SmsConfig.YiLuAn_BASE_URL + "sms.aspx";        
        String contentXml = httpsRequest.sendPost(apiUrl, paramMap);
    	log.debug(contentXml);
    	System.out.println(contentXml);
    	
    	Map<String, Object> resultMap = XMLParser.getMapFromXML(contentXml);
    	
    	return resultMap;
    }
    
    /**
     * 非法关键词查询
     * @param content
     * @return
     * @throws Exception
     */
    public Map<String, Object> checkKeyword(String content) throws Exception {
    	Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userid", SmsConfig.YiLuAn_USER_ID);
        paramMap.put("account", SmsConfig.YiLuAn_ACCOUNT);
        paramMap.put("password", SmsConfig.YiLuAn_PASSWORD);
        paramMap.put("action", "checkkeyword");
        paramMap.put("content", content);
        String apiUrl = SmsConfig.YiLuAn_BASE_URL + "sms.aspx";        
        String contentXml = httpsRequest.sendPost(apiUrl, paramMap);
    	log.debug(contentXml);
    	
    	Map<String, Object> resultMap = XMLParser.getMapFromXML(contentXml);
    	
    	return resultMap;    	
    }

    /**
     * 查询回执信息
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> queryStatus() throws Exception {
    	Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userid", SmsConfig.YiLuAn_USER_ID);
        paramMap.put("account", SmsConfig.YiLuAn_ACCOUNT);
        //paramMap.put("password", SmsConfig.YiLuAn_PASSWORD);
        paramMap.put("action", "query");
        //paramMap.put("statusNum", num);
        String apiUrl = SmsConfig.YiLuAn_BASE_URL + "statusApi.aspx";        
        String contentXml = httpsRequest.sendPost(apiUrl, paramMap);
    	log.debug(contentXml);
    	//System.out.println(contentXml);
    	
    	List<Map<String, Object>> list = XMLParser.getListFromXML(contentXml);
    	
    	return list;    	
    }
}
