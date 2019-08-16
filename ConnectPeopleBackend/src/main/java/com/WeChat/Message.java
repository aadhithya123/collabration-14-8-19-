package com.WeChat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="chatid",sequenceName="chat_seq")
public class Message
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="chatid")
	private String message;
	private int messageid;
	
	public Message()
	{
		
	}
	
	public Message(int messageid,String message)
	{
		this.message=message;
		this.messageid=messageid;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMessageid() {
		return messageid;
	}

	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	
}
