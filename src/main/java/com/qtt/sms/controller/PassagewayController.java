package com.qtt.sms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qtt.sms.model.Passageway;
import com.qtt.sms.model.PassagewayExample;
import com.qtt.sms.model.User;
import com.qtt.sms.service.LogService;
import com.qtt.sms.service.PassagewayService;
import com.qtt.sms.service.UserService;
import com.qtt.sms.util.T;

@Controller
public class PassagewayController {
	@Autowired
	PassagewayService passagewayService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LogService logService;

    @RequestMapping(value="/admin/passageway/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int pageNum = T.intValue(req.getParameter("pageNum"), 1);
    	int pageSize = T.intValue(req.getParameter("pageSize"), 25);
    	PassagewayExample example = new PassagewayExample();
    	//PassagewayExample.Criteria criteria = example.createCriteria();
    	PageInfo<Passageway> pager = passagewayService.getPager(example, pageNum, pageSize);
    	User user = userService.getCurrentUser(req, resp);
    	if (user.getId() == 1) {
    		req.setAttribute("isAdmin", true);
    	}    	
        req.setAttribute("pager", pager);
        
        return "admin/passageway/list";
    }
    
    @RequestMapping(value="/admin/passageway/create.do", method=RequestMethod.GET)
    public String showCreate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        req.setAttribute("method", "create");
        return "admin/passageway/edit";
    }
    
    @RequestMapping(value="/admin/passageway/create.do", method=RequestMethod.POST)
    public String create(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	int id = T.intValue(req.getParameter("id"), 0);
    	String name = req.getParameter("name");
    	int cmcc = T.intValue(req.getParameter("cmcc"), 0);
    	int cucc = T.intValue(req.getParameter("cucc"), 0);
    	int ctcc = T.intValue(req.getParameter("ctcc"), 0);
    	
    	if (T.isBlank(name)) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "数据不能为空"));
            return null;            		
    	}
    	if (id <= 0 || passagewayService.findById(id) != null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录已存在"));
            return null;            		      		
    	}
    	try {
    		Date now = T.getNow();
    		Passageway passageway = new Passageway();
    		passageway.setId(id);
    		passageway.setName(name);
    		passageway.setCmcc(cmcc);
    		passageway.setCucc(cucc);
    		passageway.setCtcc(ctcc);
    		passageway.setStatus(0);
    		passageway.setCreateAt(now);
    		passageway.setUpdateAt(now);
    		
    		User user = userService.getCurrentUser(req, resp);
    		passageway.setCreateBy(user.getId());
    		passageway.setUpdateBy(user.getId());
    		passagewayService.save(passageway);
    		
            logService.save(req, resp);
    	} catch (Exception e) {
    		e.printStackTrace();
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "创建失败"));
            return null;            		    	
    	}
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-passageway");
		json.put("callbackType", "closeCurrent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    @RequestMapping(value="/admin/passageway/update.do", method=RequestMethod.GET)
    public String showUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        int id = T.intValue(req.getParameter("id"), -1);
        Passageway passageway = passagewayService.findById(id);
        if (passageway == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
        req.setAttribute("method", "update");
        req.setAttribute("entity", passageway);
        return "admin/passageway/edit";
    }
    
    @RequestMapping(value="/admin/passageway/update.do", method=RequestMethod.POST)
    public String update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = T.intValue(req.getParameter("id"), -1);
        Passageway passageway = passagewayService.findById(id);
        if (passageway == null) {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "记录不存在"));
            return null;            
        }
    	String name = req.getParameter("name");
    	int cmcc = T.intValue(req.getParameter("cmcc"), 0);
    	int cucc = T.intValue(req.getParameter("cucc"), 0);
    	int ctcc = T.intValue(req.getParameter("ctcc"), 0);
    	try {
    		Passageway tmpway = new Passageway();
    		tmpway.setId(id);
    		tmpway.setCmcc(cmcc);
    		tmpway.setCucc(cucc);
    		tmpway.setCtcc(ctcc);
    		tmpway.setName(name);
    		tmpway.setUpdateAt(T.getNow());
    		tmpway.setUpdateBy(userService.getCurrentUser(req, resp).getId());
    		passagewayService.updateNoNull(tmpway);
            logService.save(req, resp);
    	} catch (Exception e) {
    		e.printStackTrace();
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(T.getJsonString(300, "更新失败"));
            return null;            		    	
    	}
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-passageway");
		json.put("callbackType", "closeCurrent");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
    
    /**
     * 更新状态
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/passageway/status.do", method=RequestMethod.POST)
    public String updateStatus(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
        String[] ids = req.getParameterValues("ids");
        int status = T.intValue(req.getParameter("status"), 0);
        if (ids != null && ids.length > 0) {
        	Passageway passageway = new Passageway();
        	passageway.setStatus(status);
        	PassagewayExample example = new PassagewayExample();
        	PassagewayExample.Criteria criteria = example.createCriteria();
            List<Integer> idList = new ArrayList<Integer>();
            StringBuffer query = new StringBuffer("{status:" + status + ", ids:");
            for (String idx : ids) {
            	query.append(idx).append(",");
            	int id = T.intValue(idx, 0);
            	if (id > 0) {
            		idList.add(id);
            	}
            }
            criteria.andIdIn(idList);
            query.append("}");
            if (!idList.isEmpty()) {
            	passagewayService.updateByExampleNotNull(passageway, example);
            	//log
            	logService.save(req, resp, query.toString());
            }
        }
        resp.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();
		json.put("statusCode", 200);
		json.put("message", "操作成功");
		json.put("navTabId", "list-passageway");
		resp.getWriter().println(json.toJSONString());
    	return null;
    }
}
