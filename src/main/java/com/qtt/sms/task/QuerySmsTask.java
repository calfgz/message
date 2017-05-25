package com.qtt.sms.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.qtt.sms.service.api.ApiService;

/**
 * @author calf
 *
 */
public class QuerySmsTask extends AbstractTask {
    
    private static boolean running = false;
    
    @Autowired
    ApiService apiService;
    
    /* (non-Javadoc)
     * @see cn.com.sms.task.AbstractTask#runTask()
     */
    @Override
    public void runTask() {
        // TODO Auto-generated method stub
        if(running) {
            log.debug("Task is running~ please try again for a moment~");
            return;
        }
        running = true;
        log.fatal("start to query sms...");
        try {
            boolean loop = true;
            while (loop) {
                int idx = apiService.querySmsByTask();
                if (idx == 0) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        loop = false;
                        e.printStackTrace();
                        break;
                    }
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            running = false;  
        }              
    }    

}
