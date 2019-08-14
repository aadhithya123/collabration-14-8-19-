package com.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.DAO.UserDetailsDAO;
import com.DBConfig.DBConfig;
import com.WeChat.UserDetails;

public class TestUser {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DBConfig.class);
		context.scan("com.DAO");
		//context.refresh();
		UserDetailsDAO userdao=(UserDetailsDAO) context.getBean(UserDetailsDAO.class);
       UserDetails obj=new UserDetails();
       obj.setEmailid("peter@gmail.com");
       obj.setIsonline("Y");
       obj.setRole("Admin");
       obj.setUserphone("99834343");
       obj.setUsername("Aadhi");
       obj.setPassword("pass123");
       obj.setUseraddress("12th street chennai-33");
       obj.setStatus("P");
       userdao.addUser(obj);
       
       System.out.println("executed");
		// TODO Auto-generated method stub

	}

}
