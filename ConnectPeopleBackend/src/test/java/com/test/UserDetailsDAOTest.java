package com.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.DAO.UserDetailsDAO;
import com.DBConfig.DBConfig;
import com.WeChat.UserDetails;

public class UserDetailsDAOTest {

	static UserDetailsDAO userdao;
	
	 
		@BeforeClass
		public static void executeFirst() 
		{
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DBConfig.class);
			context.scan("com.DAO");

			userdao=(UserDetailsDAO) context.getBean(UserDetailsDAO.class);
		}
		
		@Test
		
		public void addUserTest() {
			UserDetails user=new UserDetails();
			user.setUsername("Admin");
			user.setPassword("Admin@123");
			user.setEmailid("Admin@gmail.com");
			user.setRole("ROLE_ADMIN");
			user.setUseraddress("55th old street");
			user.setUserphone("9464465656");
			user.setIsonline("Yes");
			user.setStatus("A");
			assertTrue("Problem in Adding the UserDetails ",userdao.addUser(user));
		}
		@Test
		@Ignore
		public void updateUserTest() {
			UserDetails user=userdao.getUserDetails(4);
			user.setUsername("Aadhi");
			user.setUseraddress("Mumbai");
			
			assertTrue("Problem in Updating the UserDetails",userdao.updateUser(user));

		}
		@Test
		@Ignore
		public void checkUserCredentialTest() {
			UserDetails user=new UserDetails();
			user.setUsername("Aadhi");
			user.setPassword("pass123");
			assertNotNull("Username and Password is not matching",userdao.checkUserCredential(user));
		}
}