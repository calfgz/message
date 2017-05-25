package com.qtt.sms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qtt.sms.util.T;


@Controller
public class ImgController {

	@RequestMapping(value="/img.do")
	public String img(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String src = req.getParameter("src");
		resp.setContentType("image/jpeg");      //设置返回内容格式
		if (T.isBlank(src)) {
			return null;
		}
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(src);
		httpGet.addHeader("Accept", "image/webp,image/*,*/*;q=0.8");
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36");
		HttpResponse response = httpClient.execute(httpGet);
		
		HttpEntity entity = response.getEntity();

		// 得到网络资源的字节数组,并写入文件
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				byte b[] = new byte[1024];
				int j = 0;
				OutputStream os = resp.getOutputStream();  //创建输出流
				while( (j = instream.read(b))!= -1){
					os.write(b);
				}
			     os.flush();
			     os.close();
			} catch (IOException ex) {
				throw ex;
			} catch (RuntimeException ex) {
				httpGet.abort();
				throw ex;
			} finally {
				try { instream.close(); } catch (Exception ignore) {}
			}
		}		
		httpClient.getConnectionManager().shutdown();
		return null;
	}


	@RequestMapping(value="/download.do")
	public String download(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String src = req.getParameter("src");
		resp.setContentType("image/jpeg");      //设置返回内容格式
		if (T.isBlank(src)) {
			return null;
		}
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(src);
		httpGet.addHeader("Accept", "image/webp,image/*,*/*;q=0.8");
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36");
		HttpResponse response = httpClient.execute(httpGet);
		
		HttpEntity entity = response.getEntity();
		
		String filePathName = "aaa.jpg";
		File storeFile = new File(filePathName);
		FileOutputStream output = new FileOutputStream(storeFile);
		
		if (entity != null) {
			InputStream instream = entity.getContent();
			try {
				byte b[] = new byte[1024];
				int j = 0;
				while( (j = instream.read(b))!=-1){
				output.write(b,0,j);
				}
				output.flush();
				output.close();
			} catch (IOException ex) {
				throw ex;
			} catch (RuntimeException ex) {
				httpGet.abort();
				throw ex;
			} finally {
				try { instream.close(); } catch (Exception ignore) {}
			}
		}
		httpClient.getConnectionManager().shutdown();
		return null;
	}
}
