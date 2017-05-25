package com.qtt.sms.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.qtt.sms.model.Agent;
import com.qtt.sms.model.CounterDaily;
import com.qtt.sms.model.CounterDailyExample;
import com.qtt.sms.service.AgentService;
import com.qtt.sms.service.CounterDailyService;
import com.qtt.sms.util.T;

@Controller
public class CounterDailyController {
	@Autowired
	CounterDailyService counterDailyService;
	
	@Autowired
	AgentService agentService;

    /**
     * 列表
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/admin/counter/daily.do")
    public String daily(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	Date defaultDate = T.addDay(T.getToday(), -1);
    	Date date = T.dateValue(req.getParameter("date"), "yyyy-MM-dd", defaultDate);
    	CounterDailyExample example = new CounterDailyExample();
    	CounterDailyExample.Criteria criteria = example.createCriteria();
    	criteria.andRecDateEqualTo(date);
    	PageHelper.orderBy("success desc");
    	List<CounterDaily> list = counterDailyService.selectByExample(example);
    	String[] xAxis = new String[list.size()];
    	int[] succSeries = new int[list.size()];
    	int[] failSeries = new int[list.size()];
    	int idx = 0;
    	for(CounterDaily daily : list) {
    		if(daily.getAgentId() == 0) {
        		xAxis[idx] = "'全站'";
    		} else {
    			Agent agent = agentService.findAgentById(daily.getAgentId());
        		xAxis[idx] = "'" + agent.getName() + "'";
    		}
    		succSeries[idx] = daily.getSuccess();
    		failSeries[idx] = daily.getFail();
    		idx++;
    	}
        req.setAttribute("xAxis", Arrays.toString(xAxis));
        req.setAttribute("succSeries", Arrays.toString(succSeries));
        req.setAttribute("failSeries", Arrays.toString(failSeries));
    	
        req.setAttribute("list", list);
        req.setAttribute("date", date);
        return "admin/counter/daily";
    }
    
    @RequestMapping(value="/admin/counter/dailychat.do")
    public String dailyChat(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //check right
    	Date defaultEndDate = T.addDay(T.getToday(), -1);
    	Date defaultStartDate = T.addDay(defaultEndDate, -7);
    	Date startDate = T.dateValue(req.getParameter("startDate"), "yyyy-MM-dd", defaultStartDate);
    	Date endDate = T.dateValue(req.getParameter("endDate"), "yyyy-MM-dd", defaultEndDate);
    	int agentId = T.intValue(req.getParameter("agentId"), 0);
    	Agent agent = null;
    	if (agentId > 0) {
    		agent = agentService.findAgentById(agentId);
    	} else {
    		agent = new Agent();
    		agent.setName("全站");
    	}
    	CounterDailyExample example = new CounterDailyExample();
    	CounterDailyExample.Criteria criteria = example.createCriteria();
    	criteria.andAgentIdEqualTo(agentId);
    	criteria.andRecDateGreaterThanOrEqualTo(startDate);
    	criteria.andRecDateLessThanOrEqualTo(endDate);
    	PageHelper.orderBy("rec_date asc");
    	List<CounterDaily> list = counterDailyService.selectByExample(example);
    	String[] xAxis = new String[list.size()];
    	int[] succSeries = new int[list.size()];
    	int[] failSeries = new int[list.size()];
    	int idx = 0;
    	for(CounterDaily daily : list) {
    		xAxis[idx] = "'" + T.format(daily.getRecDate(), "yyyy-MM-dd") + "'";
    		succSeries[idx] = daily.getSuccess();
    		failSeries[idx] = daily.getFail();
    		idx++;
    	}
        req.setAttribute("xAxis", Arrays.toString(xAxis));
        req.setAttribute("succSeries", Arrays.toString(succSeries));
        req.setAttribute("failSeries", Arrays.toString(failSeries));
        req.setAttribute("startDate", startDate);
        req.setAttribute("endDate", endDate);
        req.setAttribute("agentId", agentId);
        req.setAttribute("list", list);
        req.setAttribute("agent", agent);
        return "admin/counter/dailychat";
    }
}
