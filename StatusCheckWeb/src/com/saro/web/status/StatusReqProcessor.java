package com.saro.web.status;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.saro.web.cmds.IActionCmd;
import com.saro.web.cmds.WebCmdFactory;
import com.saro.web.dataobjects.StatusWebDTO;
import com.saro.web.dataobjects.WebDTOBase;

public class StatusReqProcessor {
	String request;

	public String processrequest(String req) {

		WebDTOBase webDto = parseClientRequest(req);
		IActionCmd cmdinstance = WebCmdFactory.getWebCommandInstance(webDto);
		cmdinstance.execute();
		return webDto.getResponse();
	}

	public WebDTOBase parseClientRequest(String requestData) {

		WebDTOBase reqDto = parseReqHeader(requestData);
		
		return reqDto;

	}

	public static void main(String[] args) {
		String message = "{\"action\": \"START_MON\" ,\"data\":{\"msg\":\"Start monitor\"}}";
		
		
		WebDTOBase reqDto = parseReqHeader(message);
		

	}

	private static WebDTOBase parseReqHeader(String message) {
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(message);
		if (jsonTree.isJsonObject()) {
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement actionStr = jsonObject.get("action");
			JsonElement dataelement = jsonObject.get("data");
			StatusWebDTO reqDto = new StatusWebDTO();
			reqDto.setCommand(actionStr.getAsString());
			reqDto.setRequest(dataelement.toString());			
			System.out.println("commond set is :"+reqDto.getCommand());
			/*if (dataelement.isJsonObject()) {
				JsonObject reqDataJsonObj = dataelement.getAsJsonObject();
				JsonElement reqData = reqDataJsonObj.get("msg");
				System.out.println(actionStr.toString() + ":" + reqData);
			}*/
			return reqDto;
		}
		WebDTOBase webdto = new WebDTOBase();
		webdto.setRequest(message);
		return webdto;
	}

}
