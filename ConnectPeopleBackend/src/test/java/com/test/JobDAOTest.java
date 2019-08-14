package com.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.DAO.JobDAO;
import com.DBConfig.DBConfig;
import com.WeChat.Job;

public class JobDAOTest {

	static JobDAO jobdao;
	
	
	@BeforeClass
	public static void executeFirst() {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DBConfig.class);
		context.scan("com");

		
		jobdao=(JobDAO) context.getBean(JobDAO.class);
	}
	@Test
	
	public void addJobTest() {
		Job job=new Job();
		job.setCompanyname("IMB");
		job.setCtc(900000);
		job.setDescription("He has to know very good code skills");
		job.setDesignation("Software Developer");
		int day=12;
		int month=12;
		int year=2018;
		job.setLastdateforapply(new java.sql.Date(day,month,year));
		job.setLocation("Chennai");
		job.setSkills("Java,HTML5,Css Bootstrap,JS");
		
		assertTrue("Problem in Adding the Job",jobdao.addJob(job));
	}
	@Test
	@Ignore
	public void DisplayJobTest() 
	{
		List<Job> joblist=jobdao.displayJob();
		
		assertTrue("Problem in Retriving the jobs",joblist.size()>0);
		
		for(Job job:joblist)
		{
			System.out.println(job.getCompanyname()+":::");
			System.out.println(job.getCtc()+":::");
			System.out.println(job.getDescription()+":::");
			System.out.println(job.getDesignation()+":::");
			System.out.println(job.getLocation()+":::");
		}
	}
}
