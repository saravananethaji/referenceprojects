package com.saro.web.status;

import java.util.Timer;


public class StatusMonitor {
	
	 private Timer timer;
	 public static boolean isMonitorStarted = false;
	 private static  StatusMonitor monitorInstance=null;
	 
	 
	public static boolean isMonitorStarted() {
		return isMonitorStarted;
	}

	public static StatusMonitor getMonitorInstance( Integer seconds){
		
		
		if ((isMonitorStarted) && (monitorInstance !=null))
		{
			return monitorInstance;
		}
		
		monitorInstance = new StatusMonitor(seconds);
		return monitorInstance;
		
	}

	public static void setMonitorStarted(boolean isMonitorStarted) {
		isMonitorStarted = isMonitorStarted;
	}



	private StatusMonitor(Integer seconds){
		timer = new Timer();
        long msec = 1000;        
        timer.scheduleAtFixedRate(new StatusMonitorTask(), 0*msec, seconds.intValue()*msec);
        isMonitorStarted= true;
        
	}
	
	
	
	public void stopMonitor() {
		isMonitorStarted = false;
        timer.cancel();
        
        //display.insert("Timer has stopped.");
    }

}
