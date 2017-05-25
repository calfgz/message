package com.qtt.sms.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qtt.sms.config.SmsConfig;
import com.qtt.sms.util.HttpsRequest;
import com.qtt.sms.util.MD5;
import com.qtt.sms.util.T;

/**
 * @author zhongwm
 * 轻码云短信总入口
 */
@Service
public class QingMaYunService {    
    
    private HttpsRequest httpsRequest;
    
    public QingMaYunService() throws Exception {
        httpsRequest = new HttpsRequest();
    }

    /**
     * 发送模板短信
     * @param phone 发送手机号,多个用逗号隔开
     * @param content 发送内容
     * @return result 返回结果 
     * {"result": {"respCode": "00000","failCount": "0","smsId":"65fc35da4b854e299af4410bebce5015","createDate":"2015-12-05 09:48:24"}}
     * @throws Exception 
     */
    public String sendTemplateSms(String templateId, String phone, String content) throws Exception {
        String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);
        
        //拼装api地址
        String url = SmsConfig.BASE_URL + SmsConfig.ACCOUNT_SID + "/SMS/emailSMS?sig=" + sign + "&timestamp=" + timestamp;
        
        //拼装请求参数
        JSONObject json = new JSONObject();
        json.put("appId", SmsConfig.APP_ID);
        json.put("emailTemplateId", templateId);
        json.put("to", phone);
        json.put("param", content);
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("emailSMS", json);
        
        String result = httpsRequest.sendPost(url, jsonObject.toJSONString());
        
        return result;
    }
    
    /**
     * 免模板发送短信
     * @param phone
     * @param content 
     * @return
     * @throws Exception
     */
    public String sendContentSms(String phone, String content) throws Exception {
        return sendContentSms(phone, content, "");
    }
    
    /**
     * 免模板发送短信
     * @param phone
     * @param content 
     * @return
     * @throws Exception
     */
    public String sendContentSms(String phone, String content, String emailContent) throws Exception {
        String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        paramMap.put("appId", SmsConfig.APP_ID);
        paramMap.put("serviceType", "emailSMS");
        paramMap.put("content", content);
        if (!T.isBlank(emailContent)) {
            paramMap.put("emailContent", emailContent);
        }
        paramMap.put("to", phone);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost(SmsConfig.BASE_CONTENT_URL, paramMap);
        
        return result;
    }
    
    /**
     * 获取短信回执
     * @param smsId
     * @return {"result":{"respCode": "00000","smsBox":[{"smsId":"65fc35da4b854e299af4410bebce5015","phone":"183xxxxxxxx","status":"0","respMessage":"DELIVRD"}]}}
     * @throws Exception 
     */
    public String querySendResult(String smsId) throws Exception {
        String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);   
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        paramMap.put("appId", SmsConfig.APP_ID);
        paramMap.put("timestamp", timestamp);
        paramMap.put("smsId", smsId);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/SMS/querySendResult", paramMap);
        System.err.println("result : " + result);
        //System.out.println("sign : " + sign);
        
        return result;
    }
    
    /**
     * 获取账户信息
     * @return result {"respCode":"00000","accountSid":"30c97ed9101e48f1bb75c8ff2aacb534","developerName":"福趣","createTime":"2015-09-14 10:20:18","updateTime":"2015-09-14 10:20:18","email":"13902257771@139.com","mobile":"13902257771","activationStatus":"1","status":"2","balance":"0","giftBlance":"56800"}
     * @throws Exception
     */
    public String getAccountInfo() throws Exception {
        String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        paramMap.put("appId", SmsConfig.APP_ID);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/query/accountInfo", paramMap);
        System.err.println("result : " + result);
        
        return result;        
    }
    
    //https://api.qingmayun.com/20150822/SMS/createTemplate
    /**
     * 创建短信模板
     * @param type 模板类型。0：短信  1：邮件短信
     * @param title 模板标题。4-20位。
     * @param template 短信内容模板。不包含短信签名。
     * @param signature 短信签名。2-20位。
     * @param senderMailbox 发送者邮箱。如： sms001@139.com。邮件短信必填。请填写已经注册的139邮箱别名，对于未激活验证的139邮件别名，提交请求后，请登录139邮箱并点击激活
     * @param senderNickname 发件人昵称。用于收件人显示的发件人名称，不超过12个字符 ，如：taobao。邮件短信必填。
     * @param emailTemplate 邮件内容模板。支持html格式。邮件短信必填。
     * @return result : {"respCode":"00000","templateId":"20792170"}
     * @throws Exception
     */
    public String createTemplate(String type, String title, String template, String signature, String senderMailbox, String senderNickname, String emailTemplate) throws Exception {
        String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        paramMap.put("appId", SmsConfig.APP_ID);
        paramMap.put("templateType", type);
        paramMap.put("templateTitle", title);
        paramMap.put("smsSignature", signature);
        paramMap.put("smsTemplate", template);
        paramMap.put("senderMailbox", senderMailbox);
        paramMap.put("senderNickname", senderNickname);
        paramMap.put("emailTemplate", emailTemplate);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/SMS/createTemplate", paramMap);
        System.err.println("result : " + result);
        
        return result;
    }    

    /**
     * 修改短信模板
     * @param templateId 模板ID
     * @param type 模板类型。0：短信  1：邮件短信。（不可更改，只传值）
     * @param title 模板标题。4-20位。
     * @param template 短信内容模板。不包含短信签名
     * @param signature 短信签名。2-20位。
     * @param senderMailbox 发送者邮箱。如： sms001@139.com。邮件短信必填。请填写已经注册的139邮箱别名，对于未激活验证的139邮件别名，提交请求后，请登录139邮箱并点击激活
     * @param senderNickname 发件人昵称。用于收件人显示的发件人名称，不超过12个字符 ，如：taobao。邮件短信必填。
     * @param emailTemplate 邮件内容模板。支持html格式。邮件短信必填
     * @return
     * @throws Exception
     */
    public String modifyTemplate(String templateId, String type, String title, String template, String signature, String senderMailbox, String senderNickname, String emailTemplate) throws Exception {
        String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        //paramMap.put("appId", SmsConfig.APP_ID);
        paramMap.put("templateId", templateId);
        paramMap.put("templateType", type);
        paramMap.put("templateTitle", title);
        paramMap.put("smsSignature", signature);
        paramMap.put("smsTemplate", template);
        paramMap.put("senderMailbox", senderMailbox);
        paramMap.put("senderNickname", senderNickname);
        paramMap.put("emailTemplate", emailTemplate);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/SMS/modifyTemplate", paramMap);
        System.err.println("result : " + result);
        
        return result;
    }   

    /**
     * 查询短信模板
     * @param templateId 模板id
     * @return
     * @throws Exception
     */
    public String queryTemplate(String templateId) throws Exception {
        String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        //paramMap.put("appId", SmsConfig.APP_ID);
        paramMap.put("templateId", templateId);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/SMS/queryTemplate", paramMap);
        System.err.println("result : " + result);
        return result;
    }

    /**
     * 删除短信模板
     * @param templateId 模板id
     * @return
     * @throws Exception
     */
    public String deleteTemplate(String templateId) throws Exception {
        String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        //paramMap.put("appId", SmsConfig.APP_ID);
        paramMap.put("templateId", templateId);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/SMS/deleteTemplate", paramMap);
        System.err.println("result : " + result);
        
        return result;
    }
    
    /**
     * 发送行业短信
     * @param templateId 模板ID。创建模板提交后由系统自动分配
     * @param param 内容数据。用于替换模板中{数字}，若有多个替换内容，用|分隔
     * @param smsContent 短信内容。直接发送短信内容需要联系管理员开通权限。参数模板ID和短信内容必须输入其中一个
     * @param to 短信接收端手机号码集合。用英文逗号分开，每批发送的手机号数量不得超过100个。
     * @param portNumber 轻码云平台分配给开发者的端口号，暂不支持
     * @return
     * @throws Exception
     */
    public String sendIndustrySMS(String templateId, String param, String smsContent, String to, String portNumber) throws Exception {
    	String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        //paramMap.put("portNumber", portNumber);
        paramMap.put("templateId", templateId);
        paramMap.put("param", param);
        paramMap.put("to", to);
        paramMap.put("smsContent", smsContent);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/industrySMS/sendSMS", paramMap);
        System.err.println("result : " + result);
        
        return result;
    }
    
    /**
     * 发送营销短信
     * @param templateId 模板ID。创建模板提交后由系统自动分配
     * @param param 内容数据。用于替换模板中{数字}，若有多个替换内容，用|分隔
     * @param smsContent 短信内容。直接发送短信内容需要联系管理员开通权限。参数模板ID和短信内容必须输入其中一个
     * @param to 短信接收端手机号码集合。用英文逗号分开，每批发送的手机号数量不得超过100个。
     * @param portNumber 轻码云平台分配给开发者的端口号，暂不支持
     * @return
     * @throws Exception
     */
    public String sendAffMarkSMS(String templateId, String param, String smsContent, String to, String portNumber) throws Exception {
    	String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        //paramMap.put("portNumber", portNumber);
        paramMap.put("templateId", templateId);
        paramMap.put("param", param);
        paramMap.put("to", to);
        paramMap.put("smsContent", smsContent);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/affMarkSMS/sendSMS", paramMap);
        System.err.println("result : " + result);
        
        return result;
    }
    
    /**
     * 发送行业邮件短信
     * @param templateId 模板ID。创建模板提交后由系统自动分配
     * @param param 内容数据。用于替换模板中{数字}，若有多个替换内容，用|分隔
     * @param to 短信接收端手机号码集合。用英文逗号分开，每批发送的手机号数量不得超过100个。
     * @param portNumber 轻码云平台分配给开发者的端口号，暂不支持
     * @return
     * @throws Exception
     */
    public String sendIndustryEmailSMS(String templateId, String param, String to, String portNumber) throws Exception {
    	String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        //paramMap.put("portNumber", portNumber);
        paramMap.put("templateId", templateId);
        paramMap.put("param", param);
        paramMap.put("to", to);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/industryEmailSMS/sendEmailSMS", paramMap);
        System.err.println("result : " + result);
        
        return result;
    }
    
    /**
     * 发送营销邮件短信
     * @param templateId 模板ID。创建模板提交后由系统自动分配
     * @param param 内容数据。用于替换模板中{数字}，若有多个替换内容，用|分隔
     * @param to 短信接收端手机号码集合。用英文逗号分开，每批发送的手机号数量不得超过100个。
     * @param portNumber 轻码云平台分配给开发者的端口号，暂不支持
     * @return
     * @throws Exception
     */
    public String sendAffMarkEmailSMS(String templateId, String param, String to, String portNumber) throws Exception {
    	String timestamp = T.format(new Date(), "yyyyMMddHHmmss");
        //签名
        String sign = MD5.MD5Encode(SmsConfig.ACCOUNT_SID + SmsConfig.AUTH_TOKEN + timestamp);        
        //System.out.println("sign : " + sign);      
   
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("accountSid", SmsConfig.ACCOUNT_SID);
        //paramMap.put("portNumber", portNumber);
        paramMap.put("templateId", templateId);
        paramMap.put("param", param);
        paramMap.put("to", to);
        paramMap.put("timestamp", timestamp);
        paramMap.put("sig", sign);
        paramMap.put("respDataType", "json");
        
        String result = httpsRequest.sendPost("https://api.qingmayun.com/20150822/affMarkEmailSMS/sendEmailSMS", paramMap);
        System.err.println("result : " + result);
        
        return result;
    }

}
