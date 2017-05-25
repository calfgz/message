package com.qtt.sms.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qtt.sms.mapper.UserSessionMapper;
import com.qtt.sms.model.UserSession;

@Service
public class UserSessionService  {
    public static final String USER_SESSION = "__USER_SESSION__";
    public static final String USER_SESSION_COOKIE = "user-session";
    
    @Autowired
    UserSessionMapper userSessionMapper;
    
    public void saveSession(UserSession session, HttpServletResponse resp) {
        userSessionMapper.insert(session);
        Cookie cookie = new Cookie(USER_SESSION_COOKIE, session.getSessionId());
        resp.addCookie(cookie);
    }
    
    public UserSession recognize(HttpServletRequest req, HttpServletResponse resp) {
        UserSession session = (UserSession) req.getAttribute(USER_SESSION);
        if (session != null) {
            return session;
        }
        String sessionId = getSessionIdFromCookie(req);
        session = userSessionMapper.selectByPrimaryKey(sessionId);
        if (session == null) {
            Cookie cookie = new Cookie(USER_SESSION_COOKIE, "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        } else {
            req.setAttribute(USER_SESSION, session);
        }
        return session;
    }
    
    public String getSessionIdFromCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String sessionId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(USER_SESSION_COOKIE.equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        return sessionId;
    }
    
    public void clearSession(HttpServletRequest req, HttpServletResponse resp) {
        String sessionId = getSessionIdFromCookie(req);
        if (sessionId != null) {
            userSessionMapper.deleteByPrimaryKey(sessionId);
        }
        Cookie cookie = new Cookie(USER_SESSION_COOKIE, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
