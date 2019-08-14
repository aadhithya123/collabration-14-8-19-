package com.coll.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.DAO.FriendDAO;
import com.WeChat.Friend;
import com.WeChat.UserDetails;

@RestController
public class FriendRestController {

	@Autowired
	FriendDAO frienddao;
	
	
	//==================================SHOWPENDING FRIENDS============================================================================
	
	
	@GetMapping(value="/showPendingRequest/{username}")
	public ResponseEntity<List<Friend>> showPendingRequest(@PathVariable("username")String username)
	{  
		List<Friend> friendlist=frienddao.showPendingFriendRequest(username);
		
		if(friendlist.size()>0)
		{
			return new ResponseEntity<List<Friend>>(friendlist,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(friendlist,HttpStatus.NOT_FOUND) ;
		}
	}
	
	//==================================SHOW FRIEND LIST================================================================================
	
	
	@GetMapping(value="/showFriendList/{username}")
	public ResponseEntity<List<Friend>> showFriendRequest(@PathVariable("username")String username)
	{  
		List<Friend> friendlist=frienddao.showFriendList(username);
		
		if(friendlist.size()>0)
		{
			return new ResponseEntity<List<Friend>>(friendlist,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(friendlist,HttpStatus.NOT_FOUND) ;
		}
	}
	
	
	//==================================SHOW SUGGESTED FRIEND ================================================================================
	
	
	
	@GetMapping(value="/showSuggestedFriend/{username}")
	public ResponseEntity<List<String>> showSuggestedFriend(@PathVariable("username")String username)
	{
		List<String> suggestedfriendlist=frienddao.showSuggestedFriend(username);
		System.out.println(suggestedfriendlist);
		if(suggestedfriendlist.size()>0)
		{
			return new ResponseEntity<List<String>>(suggestedfriendlist,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<String>>(suggestedfriendlist,HttpStatus.NOT_FOUND);
		}
	}
	
	//==================================SEND FRIEND REQUEST=============================================================================
	
	
	@PostMapping(value="/sendFriendRequest")
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friend)
	{
		if(frienddao.sendFriendRequest(friend))
		{
			return new ResponseEntity<String>("New Friend Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occured while Sending Friend Request",HttpStatus.NOT_FOUND);
		}
	}
	
	//==================================DELETE FRIEND REQUEST============================================================================
	
	
	@GetMapping(value="/deleteFriendRequest/{friendid}")
	public ResponseEntity<String> deleteFriend(@PathVariable("friendid")int friendid)
	{
		if(frienddao.deleteFriendRequest(friendid))
		{
			return new ResponseEntity<String>("Successfully Deleted",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occured While Deleting Friend",HttpStatus.NOT_FOUND);
		}
	}
	
	//==================================ACCEPT FRIEND REQUEST============================================================================
	
	
	@PostMapping(value="/acceptFriendRequest/{friendid}")
	public ResponseEntity<String> acceptFriendRequest(@PathVariable("friendid")int friendid)
	{
		if(frienddao.acceptFriendRequest(friendid))
		{
			return new ResponseEntity<String>("Successfully Accepted",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occured While Accepted Friend",HttpStatus.NOT_FOUND);
		}
	}
}
