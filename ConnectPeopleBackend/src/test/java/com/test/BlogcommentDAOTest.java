package com.test;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.DAO.BlogCommentDAO;
import com.DBConfig.DBConfig;
import com.WeChat.Blogcomment;

public class BlogcommentDAOTest 
{
 static BlogCommentDAO blogcommentdao;
 
 @BeforeClass
 public static void executeFirst() {
	 AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DBConfig.class);
	 context.scan("com.DAO");
	
	blogcommentdao=(BlogCommentDAO) context.getBean(BlogCommentDAO.class);

 }
 	@Test
 
 	public void addBlogCommentTest() 
 	{
 		Blogcomment blogcomment=new Blogcomment();
 		blogcomment.setUsername("Jack");
 		blogcomment.setBlogcomment("Very Nice Name");
 		blogcomment.setBlogid(1);
 		//blogcomment.setCreatedate(createdate);
 		assertTrue("Problem in Adding the BlogComment",blogcommentdao.addcomment(blogcomment));
 	}
 	@Test
 	@Ignore
 	public void ListBlogComment() 
 	{
 		List<Blogcomment> commentlist=blogcommentdao.listBlogcomment(152);
 		assertTrue("Problem in Displaying the Blogcomment",commentlist.size()>0);
 		for(Blogcomment comment:commentlist)
 		{
 			System.out.println(comment.getBlogcomment()+":::");
 			System.out.println(comment.getUsername());
 		}
 	}
 	@Test
 	@Ignore
 	public void deleteBlogComment()
 	{
 		Blogcomment comment=blogcommentdao.getBlogcomment(52);
 		assertTrue("Problem in Deleting the BlogComment",blogcommentdao.deletecomment(comment));
 	}
}
