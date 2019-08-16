package com.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.WeChat.Blogcomment;
@Repository
@Transactional
public class BlogcommentDaoImpl implements BlogCommentDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	//========================================ADD BLOG COMMENT====================================================================
	
	
	@Override
	public boolean addcomment(Blogcomment comment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(comment);
		return true;
	}

	
	//========================================DELETE BLOG COMMENT===================================================================
	
	
	@Override
	public boolean deletecomment(Blogcomment comment) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(comment);
		return true;
	}

	
	//========================================LIST BLOG COMMENT====================================================================
	
	
	@Override
	public List<Blogcomment> listBlogcomment(int id) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Blogcomment where blogid=:id");
		query.setParameter("id", id);
		List<Blogcomment> commentlist=query.list(); 
		return commentlist;
	}

	
	//========================================FIND BLOG COMMENT====================================================================
	
	
	@Override
	public Blogcomment getBlogcomment(int blogcommentid) {
		Session session=sessionFactory.getCurrentSession();
		Blogcomment blogcomment=session.get(Blogcomment.class,blogcommentid);
	
		return blogcomment;
	}

}
