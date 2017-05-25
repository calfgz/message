package com.qtt.sms.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.qtt.sms.service.CounterMonthlyService;
import com.qtt.sms.util.T;

public class CounterMonthlyTask extends AbstractTask {
    
    private static boolean running = false;
    
    @Autowired
    CounterMonthlyService counterMonthlyService;

	@Override
	public void runTask() {
		// TODO Auto-generated method stub
        if(running) {
            log.debug("Task is running~ please try again for a moment~");
            return;
        }
        running = true;
        log.fatal("start to counter daily...");
        try {
    		Date date = T.addMonth(T.getNow(), -1);
    		counterMonthlyService.monthlyCounter(date);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            running = false;  
        }     
		
	}

}
