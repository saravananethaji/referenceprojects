package com.saro.web.cmds;

import com.saro.web.dataobjects.WebDTOBase;
import com.saro.web.status.StatusMonitor;

public class StartMonitorCommand implements IActionCmd {
	public WebDTOBase webDTO= null;
	
	public StartMonitorCommand(WebDTOBase webdto){
		webDTO=webdto;
	}
	

	public void execute(){
		if (StatusMonitor.isMonitorStarted()){
			webDTO.setResponse("monitor already running");
			return ;
		}
		StatusMonitor monitor =  StatusMonitor.getMonitorInstance(60);
		webDTO.setResponse("monitor initiated");
		
	};
}
