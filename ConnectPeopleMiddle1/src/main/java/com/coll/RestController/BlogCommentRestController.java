package com.coll.RestController;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.DAO.BlogCommentDAO;
import com.WeChat.Blogcomment;

import oracle.jdbc.proxy.annotation.Post;

@RestController
public class BlogCommentRestController
	{
    @Autowired
    BlogCommentDAO blogcommentdao;
    
    //=========================GETALL BLOGCOMMENT==================================================================================
    
    @GetMapping(value="/getAllBlogComment/{blogid}")
    public ResponseEntity<List<Blogcomment>> getAllBlogComment(@PathVariable("blogid")int blogid)
    {
    	List<Blogcomment> blogcommentlist=blogcommentdao.listBlogcomment(blogid);
    	
    	if(blogcommentlist.size()>0)
    	{
    		return new ResponseEntity<List<Blogcomment>>(blogcommentlist,HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<List<Blogcomment>>(blogcommentlist,HttpStatus.NOT_FOUND);
    	}
    }
    
  //=========================ADD BLOGCOMMENT==================================================================================
    
    @PostMapping(value="/addBlogComment")
    public ResponseEntity<String> addBlogComment(@RequestBody Blogcomment blogcomment)
    {
    	java.util.Date dt=new java.util.Date();
  	  long millis=System.currentTimeMillis();
  	  java.sql.Date date=new java.sql.Date(millis);
  		blogcomment.setCreatedate(date);
    	if(blogcommentdao.addcomment(blogcomment))
    	{
    		return new ResponseEntity<String>("BlogComment Added",HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<String>("Problem Occuring While Adding BlogComment",HttpStatus.NOT_FOUND);
    	}
    }
    
  //=========================DELETE BLOGCOMMENT==================================================================================
    
    @PostMapping(value="/deleteBlogComment/{blogcommentid}",produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> deleteBlogComment(@PathVariable("blogcommentid")int blogid)
    {
    	Blogcomment blogcomment=blogcommentdao.getBlogcomment(blogid);
    	
    	if(blogcommentdao.deletecomment(blogcomment))
    	{
    		return new ResponseEntity<String>("BlogComment Deleted",HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<String>("Problem Occuring While Deleting blogComment",HttpStatus.NOT_FOUND);
    	}
    }
}
