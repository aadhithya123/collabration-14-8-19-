package com.DAO;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.WeChat.UserDetails;

@Repository
@Transactional
public class UserDetailsDaoImpl implements UserDetailsDAO{
	@Autowired
	SessionFactory sessionfactory;
	
	
	//====================================ADD USER========================================================================
	
	
	@Override
	public boolean addUser(UserDetails user) {
		Session session=sessionfactory.getCurrentSession();
		session.save(user);
		return true;
	}

	
	//====================================UPDATE USER=====================================================================
	
	
	@Override
	public boolean updateUser(UserDetails user) {
		Session session=sessionfactory.getCurrentSession();
		session.update(user);
		return true;
	}
	
	//====================================GET USER DETAILS=================================================================
	

	@Override
	public UserDetails getUserDetails(int userid) {
		Session session=sessionfactory.getCurrentSession();
		UserDetails user=session.get(UserDetails.class,userid);
		
		return user;
	}

	
	//====================================CHECK UESR CREDENTIAL=============================================================
	
	
	@Override
	public UserDetails checkUserCredential(UserDetails user) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from  UserDetails  where username=:uname and password=:pword");
		query.setParameter("uname",user.getUsername());
		query.setParameter("pword",user.getPassword());
		
		if(query.getResultList().size()>0)
		{
			UserDetails user1=(UserDetails) query.getResultList().get(0);
			return user1;
		}
		else
		{
			return null;
		}
			
	}
    
}
