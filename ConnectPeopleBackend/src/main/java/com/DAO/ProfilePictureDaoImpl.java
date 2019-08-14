package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.WeChat.ProfilePicture;

@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDAO {

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean addProfilePicture(ProfilePicture pic) {
		Session session=sessionFactory.getCurrentSession();
		session.save(pic);
		System.out.println(pic);
		return true;
	}

	@Override
	public ProfilePicture getProfilePicture(String username) {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture pic=session.get(ProfilePicture.class,username);
		return pic;
	}

}
