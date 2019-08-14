package com.test;



import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.DAO.FriendDAO;
import com.DBConfig.DBConfig;
import com.WeChat.Friend;

public class FriendDAOTest 
{
  static FriendDAO frienddao;
  
 @BeforeClass
 public static void executeFirst()
 {
	 AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DBConfig.class);
	 context.scan("com.DAO");

	 
	 frienddao=(FriendDAO) context.getBean(FriendDAO.class);
 }
 
 @Test

 public void sendFriendRequestTest()
 {
	 Friend friend=new Friend();
	 friend.setUsername("Aadhi");
	 friend.setFriendname("Raj");
	 friend.setStatus("P");
	 
	 assertTrue("Problem in sending Friend Request",frienddao.sendFriendRequest(friend));
 }
 @Test
 @Ignore
 public void acceptFriendRequest()
 {
	 assertTrue("Problem in Accepting Friend Request",frienddao.acceptFriendRequest(1));
 }
 @Test
 @Ignore
 public void deleteFriendRequest()
 {
	 assertTrue("Problem in Deleting Friend Request",frienddao.deleteFriendRequest(52));
 }
}
