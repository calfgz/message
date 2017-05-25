package com.qtt.sms.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpsRequest{

    //表示请求器是否已经做了初始化工作
    private boolean hasInit = false;

    //连接超时时间，默认10秒
    private int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;

    //请求器的配置
    private RequestConfig requestConfig;

    //HTTP请求器
    private CloseableHttpClient httpClient;

    public HttpsRequest() throws Exception {
        init();
    }

    private void init() throws Exception {
        httpClient = HttpClients.custom().build();
        
        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        
        /*if (Config.getProxy()) {
            //代理
            HttpHost proxy = new HttpHost(Config.getProxyHost(), Config.getProxyPort(), "HTTP");
            requestConfig = RequestConfig.custom().setProxy(proxy).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        } else {
            //根据默认超时限制初始化requestConfig
            requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        }*/

        hasInit = true;
    }

    /**
     * 通过Https往API post 数据     *
     * @param url    API地址
     * @param paramsMap 请求参数
     * @return API回包的实际数据
     * @throws Exception
     */
    public String sendPost(String url, String jsonString) throws Exception {

        if (!hasInit) {
            init();
        }

        String result = null;

        //System.out.println("url:" + url);
        HttpPost httpPost = new HttpPost(url);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别    
        StringEntity postEntity = new StringEntity(jsonString, "UTF-8");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setEntity(postEntity);        

        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            System.err.println("http post throw Exception");
            e.printStackTrace();
        } finally {
            httpPost.abort();
        }

        return result;
    }

    /**
     * 通过Https往API post 数据     *
     * @param url    API地址
     * @param paramsMap 请求参数
     * @return API回包的实际数据
     * @throws Exception
     */
    public String sendPost(String url, Map<String, String> paramsMap) throws Exception {

        if (!hasInit) {
            init();
        }

        String result = null;

        //System.out.println("url:" + url);
        HttpPost httpPost = new HttpPost(url);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别    
        //StringEntity postEntity = new StringEntity(jsonString, "UTF-8");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for(Entry<String, String> entry : paramsMap.entrySet()) {            
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(params, "UTF-8");
        //httpPost.addHeader("Accept", "application/json");
        //httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setEntity(postEntity);        

        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            System.err.println("http sendpost throw Exception");
            e.printStackTrace();
        } finally {
            httpPost.abort();
        }

        return result;
    }
    

    /*@SuppressWarnings("unused")
    private void resetRequestConfig(){
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }*/
}
