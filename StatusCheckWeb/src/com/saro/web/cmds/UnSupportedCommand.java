package com.saro.web.cmds;

import com.saro.web.dataobjects.WebDTOBase;
import com.saro.web.status.StatusMonitor;

public class UnSupportedCommand implements IActionCmd {
	public WebDTOBase webDTO= null;
	
	public UnSupportedCommand(WebDTOBase webdto){
		webDTO=webdto;
	}
	

	public void execute(){
		
		webDTO.setResponse("Invalid Command.Command UnSupported");
		
	};
}
