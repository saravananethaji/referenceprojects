package com.saro.web.cmds;

import com.saro.web.dataobjects.WebDTOBase;
import com.saro.web.status.StatusMonitor;

public class StopMonitorCommand implements IActionCmd {
	public WebDTOBase webDTO= null;
	
	public StopMonitorCommand(WebDTOBase webdto){
		webDTO=webdto;
	}
	

	public void execute(){
		if (!StatusMonitor.isMonitorStarted()){
			webDTO.setResponse("monitor already stopped");
			return ;
		}
		StatusMonitor monitor =  StatusMonitor.getMonitorInstance(60);
		monitor.stopMonitor();
		StatusMonitor.setMonitorStarted(false);
		webDTO.setResponse("monitor stopped");
		
	};
}
