package com.qtt.sms.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * kaptcha验证码
 * @author zhongwm
 * 
 */
@Controller
public class CaptchaImageController {
    
    @Autowired
    Producer captchaProducer;
    
    @RequestMapping(value="/captcha.do", method=RequestMethod.GET)
    public String captcha(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setDateHeader("Expires", 0); 
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        resp.setHeader("Pragma", "no-cache");           
        resp.setContentType("image/jpeg");  
  
        String capText = captchaProducer.createText();  
        
        req.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = resp.getOutputStream();
        
        ImageIO.write(bi, "jpg", out);
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }
   
}
