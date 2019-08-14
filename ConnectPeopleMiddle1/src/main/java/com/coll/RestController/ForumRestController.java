package com.coll.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.DAO.ForumDAO;
import com.WeChat.Blog;
import com.WeChat.Forum;
import com.WeChat.UserDetails;

@RestController
public class ForumRestController {

	@Autowired
	ForumDAO forumdao;
	
	
	//=========================================SHOW FORUM===============================================================================
	
	
	
	@GetMapping(value="showAllForum")
	public ResponseEntity<List<Forum>> showAllForum()
	{
		List<Forum> forumlist=forumdao.listForum();
		if(forumlist.size()>0)
		{
			return new ResponseEntity<List<Forum>>(forumlist,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Forum>>(forumlist,HttpStatus.NOT_FOUND);
		}
	}
	
	
	//=========================================ADD FORUM===============================================================================
	
	
	@PostMapping(value="addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum,HttpSession session)
	{
		UserDetails  details=(UserDetails)session.getAttribute("userdetail");
		 java.util.Date dt=new java.util.Date();
		  long millis=System.currentTimeMillis();
		  java.sql.Date date=new java.sql.Date(millis);
			forum.setCreatedate(date);
			forum.setUsername(details.getUsername());
			forum.setStatus("A");
			
			if(forumdao.addForum(forum))
			{
				return new ResponseEntity<String>("Forum is Added",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Error Occured",HttpStatus.NOT_FOUND);
			}
	}
	
	
	//=========================================UPDATE FORUM===============================================================================
	
	
	@PostMapping(value="/updateForum",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateForum(@RequestBody Forum forum)
	{
		
		Forum  forumup=forumdao.getForum(forum.getForumid());
		forumup.setUsername(forum.getUsername());
		forumup.setForumcontains(forumup.getForumcontains());
		forumup.setStatus(forum.getStatus());
		
		
		if(forumdao.updateForum(forumup))
		{
			return new ResponseEntity<String>("Forum Updated",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occuring While Update Forum",HttpStatus.NOT_FOUND);
		}
	}
	
	
	//=========================================DELETE FORUM===============================================================================
	
	
	
	@PostMapping(value="deleteForum/{forumid}")
	public ResponseEntity<?> deleteForum(@PathVariable("forumid")int forumid)
	{
		Forum forum=forumdao.getForum(forumid);
		if(forumdao.delete(forum))
		{
			return new ResponseEntity("Forum Deleted",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Problem Occuring While Deleting",HttpStatus.NOT_FOUND);
		}
	}
	
	
	//=========================================GET FORUM===============================================================================
	
	
	@GetMapping(value="getForum/{forumid}")
	public ResponseEntity<Forum> getForum(@PathVariable("forumid") int forumid)
	{
		Forum forum=forumdao.getForum(forumid);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
    }
}