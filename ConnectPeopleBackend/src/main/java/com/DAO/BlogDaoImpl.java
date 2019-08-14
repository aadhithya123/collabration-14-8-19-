package com.DAO;

import com.WeChat.*;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.WeChat.Blog;
@Repository
@Transactional
public class BlogDaoImpl implements BlogDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	//========================================ADD BLOG======================================================================
	
	
	@Override
	public boolean addBlog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blog);
		return true;
	}

	//========================================UPDATE BLOG====================================================================
	
	
	@Override
	public boolean updateBlog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		session.update(blog);
		return true;
	}

	//========================================DELETE BLOG====================================================================
	
	
	@Override
	public boolean deleteblog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(blog);
		return true;
	}
	
	
	//========================================FIND BLOG======================================================================
	

	@Override
	public Blog getBlog(int blogid) {
		Session session=sessionFactory.getCurrentSession();
	     Blog blog=session.get(Blog.class, blogid);
	  
		return blog;
	}
	
	//========================================INCREMENT LIKES=================================================================
	
	
	@Override
	public boolean incrementLikes(int blogid) {
		Blog blog=this.getBlog(blogid);
		blog.setLikes(blog.getLikes()+1);
		Session session=sessionFactory.getCurrentSession();
		session.update(blog);
		return true;
	}
	
	//========================================INCREMENT DISLIKES==============================================================
	

	@Override
	public boolean incrementDisLikes(int blogid) {
		Blog blog=this.getBlog(blogid);
		blog.setDislike(blog.getDislike()+1);
		Session session=sessionFactory.getCurrentSession();
		return true;
	}

	//========================================APPROVE BLOG====================================================================
	
	
	@Override
	public boolean approveBlog(Blog blog) {
		blog.setStatus("A");
		Session session=sessionFactory.getCurrentSession();
		session.update(blog);
		return true;
	}

	
	//========================================REJECT BLOG====================================================================
	
	
	@Override
	public boolean rejectBlog(Blog blog) {
		blog.setStatus("P");
		Session session=sessionFactory.getCurrentSession();
		session.update(blog);
		return true;
	}
	
	//========================================LIST THE BLOG====================================================================
	
	
	@Override
	public List<Blog> listBlog() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blog");
		List<Blog> bloglist=query.getResultList();
		
		return bloglist;
	}

}
