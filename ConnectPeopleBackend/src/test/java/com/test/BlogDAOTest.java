package com.test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.DAO.BlogDAO;
import com.DBConfig.DBConfig;
import com.WeChat.Blog;
//import com.apple.eawt.Application;

public class BlogDAOTest
{
	@Autowired
	static BlogDAO blogdao;
	 
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DBConfig.class);
		context.scan("com.DAO");

		blogdao=(BlogDAO) context.getBean(BlogDAO.class);
	}
	
	@Test
	
	public void addBlogTest() {
		Blog blog=new Blog();
		blog.setBlogname("SpaceBlack");
		blog.setBlogcontent("It is a Nice Movie");
		int day=12;
		int month=12;
		int year=2018;
		blog.setCreateDate(new java.sql.Date(day,month,year));
		blog.setLikes(0);
		blog.setDislike(0);
		blog.setUsername("Aadhi");
		blog.setStatus("P");
		assertTrue("Problem adding in Blog",blogdao.addBlog(blog));
	}
	
	
	@Test
	@Ignore
	public void updateBlogTest()
	{
		Blog blog=blogdao.getBlog(102);
		
		blog.setBlogcontent("This Blog Contant Devops Information");
		assertTrue("Problem in Update the blog",blogdao.updateBlog(blog));
	}
	@Test
	@Ignore
	public void approveBlogTest() {
		Blog blog=blogdao.getBlog(102);
		
		assertTrue("Problem in Approve the Blog",blogdao.approveBlog(blog));
	}
	
	
	@Test
	@Ignore
	public void rejectBlogTest() 
	{
		Blog blog =blogdao.getBlog(102);
		assertTrue("Problem in reject the Blog",blogdao.rejectBlog(blog));
	}
	
	
	@Test
	@Ignore
	public void incrementLikesBlogTest()
	{
		assertTrue("Problem in Approving the Blog",blogdao.incrementLikes(102));
	}
	
	
	@Test
	@Ignore
	public void incrementDislikeBlogTest() 
	{
		assertTrue("Problem in Approving the Blog" , blogdao.incrementDisLikes(102));
	}
	
	
	@Test
	@Ignore
	public void deleteBlogTest() 
	{
		Blog blog=blogdao.getBlog(102);
		assertTrue("Problem in Deleting the Blog",blogdao.deleteblog(blog));
	}
}
