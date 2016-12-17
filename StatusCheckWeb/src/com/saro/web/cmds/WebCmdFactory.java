package com.saro.web.cmds;

import com.saro.web.dataobjects.WebDTOBase;

public class WebCmdFactory {
	public static IActionCmd getWebCommandInstance(WebDTOBase webdto){
		
		return getCmdInstance(webdto);
	}
	
	public static IActionCmd getCmdInstance(WebDTOBase webdto)
	{
		String cmd = webdto.getCommand();
		if (cmd == null)
				return new UnSupportedCommand(webdto);
		
		IActionCmd cmdinstance =null; 
		
		
		switch (cmd) {
		 case "START_MON":{
			 cmdinstance= new StartMonitorCommand(webdto);
			
			 break;
		 
		 }
		 case "STOP_MON":{
			 cmdinstance= new StopMonitorCommand(webdto);
			
			 break;
		 
		 }
		 
		 default:
			 cmdinstance= new UnSupportedCommand(webdto);
			 
		 }
		return cmdinstance;
		
	}

}
