package com.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.WeChat.Job;

@Repository
@Transactional
public class JobDaoImpl implements JobDAO{
	@Autowired
	SessionFactory sessionFactory;

	//==================================ADD JOB====================================================================
	
	
	public boolean addJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
		return true;
	}
	
	
	//==================================DISPLAY JOB================================================================
	
//	
//	public Job displayJob(Job job) {
//		Session session=sessionFactory.getCurrentSession();
//		
//		return null;
//	}

	
	//==================================LIST OF JOB=================================================================
	
	
	@Override
	public List<Job> displayJob() {
		Session session =sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Job> joblist=session.createQuery("from Job").list();
		
		return joblist;
	}

	
	//==================================DELETE JOB====================================================================
	
	
	@Override
	public boolean delete(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session .delete(job);
		return true;
	}

	
	//==================================FIND JOB DETAILS===============================================================
	
	
	@Override
	public Job getJobDetails(int jobid) {
		Session session =sessionFactory.getCurrentSession();
		Job job=session.get(Job.class, jobid);
		
		return job;
	}

}
