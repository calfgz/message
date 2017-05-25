package com.qtt.sms.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qtt.sms.mapper.AgentSessionMapper;
import com.qtt.sms.model.AgentSession;

@Service
public class AgentSessionService  {
    public static final String AGENT_SESSION = "__AGENT_SESSION__";
    public static final String AGENT_SESSION_COOKIE = "agent-session";
    
    @Autowired
    AgentSessionMapper agentSessionMapper;
    
    public void saveSession(AgentSession session, HttpServletResponse resp) {
        agentSessionMapper.insert(session);
        Cookie cookie = new Cookie(AGENT_SESSION_COOKIE, session.getSessionId());
        resp.addCookie(cookie);
    }
    
    public AgentSession recognize(HttpServletRequest req, HttpServletResponse resp) {
        AgentSession session = (AgentSession) req.getAttribute(AGENT_SESSION);
        if (session != null) {
            return session;
        }
        String sessionId = getSessionIdFromCookie(req);
        session = agentSessionMapper.selectByPrimaryKey(sessionId);
        if (session == null) {
            Cookie cookie = new Cookie(AGENT_SESSION_COOKIE, "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        } else {
            req.setAttribute(AGENT_SESSION, session);
        }
        return session;
    }
    
    public String getSessionIdFromCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String sessionId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(AGENT_SESSION_COOKIE.equals(cookie.getName())) {
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
            agentSessionMapper.deleteByPrimaryKey(sessionId);
        }
        Cookie cookie = new Cookie(AGENT_SESSION_COOKIE, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
