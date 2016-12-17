package com.saro.web.status;



import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;



@ServerEndpoint("/status")
public class StatusWebSocketServer {
	
	private static Set<Session> allSessions = new HashSet <Session>(); 
	
	 @OnOpen
     public void open(Session session) {
		 System.out.println("socket opened");
		 try {
			 
			 session.getBasicRemote().sendText("Connection Established");
			
			 allSessions.add(session);
	            
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
 }

 @OnClose
     public void close(Session session) {
	 System.out.println("Session " +session.getId()+" has ended");
	 
	 synchronized (allSessions) {
		allSessions.remove(session);
	
	}
	 
 }

 @OnError
     public void onError(Throwable error) {
	 System.out.println("socket onError");
	 error.printStackTrace();
 }
 
 
 

 @OnMessage
     public void handleMessage(String message, Session session) {
	
	 System.out.println("Message from " + session.getId() + ": " + message);
	 StatusReqProcessor processor = new StatusReqProcessor();
	 String responsemsg = processor.processrequest(message);
	 
	 
     synchronized (session){
	 try {
         session.getBasicRemote().sendText(responsemsg);
     } catch (IOException ex) {
         ex.printStackTrace();
     }
     }
 }
 
 
 public static void broadcastMsg( String broadCastMsg){       
     
     for (Session sess: allSessions){          
    	 synchronized (sess){
    	 try{   
          sess.getBasicRemote().sendText(broadCastMsg);
          } catch (IOException ioe) {        
              System.out.println(ioe.getMessage());         
          }   
    	 }
     }   
  }

}
