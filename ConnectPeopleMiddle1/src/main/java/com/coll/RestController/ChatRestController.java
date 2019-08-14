package com.coll.RestController;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.WeChat.Message;
import com.WeChat.OutputMessage;

@RestController 
public class ChatRestController {

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message)
	{
		  int day=new java.util.Date().getDate();
		  int month=new java.util.Date().getMonth()+1;
		  int year=new java.util.Date().getYear();
				  
		return new OutputMessage(message,new Date(day, month,year));
	}
}
