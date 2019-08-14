package com.coll.RestController;

import java.sql.Date;
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
import com.DAO.BlogDAO;
import com.WeChat.Blog;
import com.WeChat.UserDetails;

@RestController
public class BlogRestController {

	@Autowired
	BlogDAO blogdao;
	
	//==============================SHOWALL BLOG=======================================================================
	
	@GetMapping(value="showAllBlog")
	public ResponseEntity<List<Blog>> showAllBlog()
  {
	List<Blog> bloglist=blogdao.listBlog();	
		
	if(bloglist.size()>0)
	{
	 return new ResponseEntity<List<Blog>>(bloglist,HttpStatus.OK);
	}
	else
	{
		 return new ResponseEntity<List<Blog>>(bloglist,HttpStatus.NOT_FOUND);
	}
 }
	
    //==============================ADD BLOG=======================================================================
	
	
	@PostMapping(value="addBlog",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addBlog(@RequestBody Blog blog,HttpSession session)
   {
	  java.util.Date dt=new java.util.Date();
	  long millis=System.currentTimeMillis();
	  java.sql.Date date=new java.sql.Date(millis);
		blog.setStatus("P");
		 UserDetails user=(UserDetails)session.getAttribute("userdetail");
		blog.setUsername(user.getUsername());
		blog.setCreateDate(date);
		if(blogdao.addBlog(blog))
		{
			return new ResponseEntity<String>("Blog Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occured",HttpStatus.NOT_FOUND);
		}
	}
	
	//==============================DELETE BLOG=======================================================================
	
	@PostMapping(value="/deleteBlog/{blogid}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity deleteBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=blogdao.getBlog(blogid);
		 
		if(blogdao.deleteblog(blog))
		{
			return new ResponseEntity("Blog Deleted",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Problem Occured While Blog Deleting",HttpStatus.NOT_FOUND);
		}
	}
	
	//==============================INCREMENTLIKES BLOG=======================================================================
	
	@PostMapping(value="/incrementLikes/{blogid}")
	public ResponseEntity<String>  incrementLike(@PathVariable("blogid")int blogid)
	{
		if(blogdao.incrementLikes(blogid))
		{
		return new ResponseEntity<String>("Blog like increment",HttpStatus.OK);
	    }
	else
	   {
		return new ResponseEntity<String>("Problem Occuring While incrementing Likes",HttpStatus.NOT_FOUND);
	   }
	}
	
	
	//==============================INCREMENTDISLIKES BLOG=======================================================================
	
	@PostMapping(value="/incrementDisLikes/{blogid}")
	public ResponseEntity<String> incrementDislikes(@PathVariable("blogid")int blogid)
	{
		if(blogdao.incrementDisLikes(blogid))
		{
			return new ResponseEntity<String>("Blog Dislikes increment",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occuring While incrementing Dislikes",HttpStatus.NOT_FOUND);
		}
	}
	
	//==============================APPROVE BLOG=======================================================================
	
	@PostMapping(value="/approveBlog/{blogid}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> approveBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=blogdao.getBlog(blogid);
		if(blogdao.approveBlog(blog))
		{
			return new ResponseEntity<String>("Blog Approve",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occuring While Approving Blog",HttpStatus.NOT_FOUND);
		}
	}
	
	
	//==============================REJECT BLOG=======================================================================
	
	@PostMapping(value="/rejectBlog/{blogid}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> rejectBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=blogdao.getBlog(blogid);
		
		if(blogdao.rejectBlog(blog))
		{
			return new ResponseEntity<String>("Blog rejecting",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occuring While rejecting Blog",HttpStatus.NOT_FOUND);
		}
	}
	
	//==============================UPDATE BLOG=======================================================================
	
	@PostMapping(value="/updateBlog",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
	{
		Blog tempBlog=blogdao.getBlog(blog.getBlogid());
		tempBlog.setBlogname(blog.getBlogname());
		tempBlog.setBlogcontent(blog.getBlogcontent());
		
		if(blogdao.updateBlog(tempBlog))
		{
			return new ResponseEntity<String>("blog Updated",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occuring While Update Blog",HttpStatus.NOT_FOUND);
		}
	}
	
	//==============================GET BLOG=======================================================================
	
	@GetMapping(value="/getSingleBlog/{blogid}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=blogdao.getBlog(blogid);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
}
