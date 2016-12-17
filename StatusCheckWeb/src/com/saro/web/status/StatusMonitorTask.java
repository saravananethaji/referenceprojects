package com.saro.web.status;

import java.util.TimerTask;

public class StatusMonitorTask extends TimerTask{



@Override
public void run() {
	// TODO Auto-generated method stub
	 // task to do
	
	System.out.println("Monitor task is getting executed");
	StatusWebSocketServer.broadcastMsg("monitor task is running");
	
}
}