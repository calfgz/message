package com.qtt.sms.config;

/**
 * 短信发送系统常量
 */
public class SmsConfig {
	/**
	 * url前半部分
	 */
	public static final String BASE_URL = "http://api.qingmayun.com/20141029/accounts/";
	
	/**
	 * 免模板信息url
	 */
	public static final String BASE_CONTENT_URL = "http://api.qingmayun.com/20150822/SMS/contentSMS";

	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static final String ACCOUNT_SID = "30c97ed9101e48f1bb75c8ff2aacb534";

	/**
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static final String AUTH_TOKEN = "24e3a62acf644a2697a960c16f0d2dfa";

	/**
	 * 应用id
	 */
	public static final String APP_ID = "8e4967cd04114dc4932d81083e97b876";
	
	/**
	 * client账号
	 */
	//public static final String CLIENT_NUMBER = "774084516xxx";
	
	/**
	 * client账号的密码
	 */
	//public static final String CLIENT_PWD = "xu96RNaW";
	
	/**
	 * 响应数据类型, JSON或XML
	 */
	public static final String RESP_DATA_TYPE = "json";
	
	/**
	 * 模板id 
	 */
    public static final String Template_Id = "1700102";
    
    /************************************
     * 
     * 易路安短信
     * 
     ***********************************/
    /**
     * 接口地址
     */
    public static final String YiLuAn_BASE_URL = "http://112.90.184.138:8888/";
    
    public static final String YiLuAn_USER_ID = "77";
    
    public static final String YiLuAn_ACCOUNT = "DX-xh01";
    
    public static final String YiLuAn_PASSWORD = "Dkjh998Ff";
}