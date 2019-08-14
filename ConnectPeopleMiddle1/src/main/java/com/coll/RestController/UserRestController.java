package com.coll.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.DAO.UserDetailsDAO;
import com.WeChat.UserDetails;

@RestController
public class UserRestController
  {
 
	@Autowired
	UserDetailsDAO userdetaildao;
	
	
	//===================================ADD USERDETAILS=====================================================================
	
	
   @PostMapping(value="/addUser")
   public ResponseEntity<String> registerUser(@RequestBody UserDetails user)
   {
	   user.setRole("ROLE_USER");
	   user.setIsonline("Yes");
	   user.setStatus("A");
	   if(userdetaildao.addUser(user))
	   {
		   return new ResponseEntity<String>("UserDetails Added",HttpStatus.OK);
	   }
	   else
	   {
		   return new ResponseEntity<String>("Problem Occuring While Adding UserDetails",HttpStatus.NOT_FOUND);
	   }
   }
   
   
 //===================================UPDATE USERDETAILS=====================================================================
   
   
   @PostMapping(value="/updateUser",produces=MediaType.TEXT_PLAIN_VALUE)
   public ResponseEntity<String> updateUser(@RequestBody UserDetails userdetail)
   {
	   UserDetails usertemp=userdetaildao.getUserDetails(userdetail.getUserid());
	   
	   System.out.println("Temp user Address:"+usertemp.getUseraddress());
	   System.out.println("User Address:"+userdetail.getUseraddress());
	   usertemp.setEmailid(userdetail.getEmailid());
	   usertemp.setUseraddress(userdetail.getUseraddress());
	   usertemp.setUserphone(userdetail.getUserphone());
	   usertemp.setUsername(userdetail.getUsername());
	   usertemp.setStatus(userdetail.getStatus());
	   usertemp.setIsonline(userdetail.getIsonline());
	   
	if(userdetaildao.updateUser(usertemp))
	{
		return new ResponseEntity<String>("UserDetails Updated",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("Problem Occuring While Updating",HttpStatus.NOT_FOUND);
	}
  }
   
   
 //===================================GET USERDETAILS=====================================================================
   
   
   @GetMapping(value="/get/{userid}")
   public ResponseEntity<UserDetails> getUser(@PathVariable("userid")int userid)
   {
	   UserDetails user=userdetaildao.getUserDetails(userid);
	   return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
   }
   
   
 //===================================CHECK USERDETAILS=====================================================================
   
   
   @PostMapping(value="/checkUser")
   public ResponseEntity<UserDetails> checkUser(@RequestBody UserDetails user , HttpSession session,HttpServletResponse res)
   { 
	   System.out.println("user");
	   
	   UserDetails user1=userdetaildao.checkUserCredential(user);
	   
	   if(user1!=null)
	   {
		   session.setAttribute("userdetail", user1);
		   return new ResponseEntity<UserDetails>(user1,HttpStatus.OK);
	   }
	   else
	   {
		  return new ResponseEntity<UserDetails>(user1,HttpStatus.NOT_FOUND);
	   }
   }
   
   
}
