package com.coll.RestController;


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

import com.DAO.JobDAO;
import com.WeChat.Job;

@RestController
public class JobRestController {
	
	@Autowired
	JobDAO jobdao;
	
	
	//===================================ADD JOB==============================================================================
	
	
	@PostMapping(value="/addJob")
	public ResponseEntity<String> registorJob(@RequestBody Job job)
	{
		if(jobdao.addJob(job))
		{
			return new ResponseEntity<String>("Job Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occuring While Adding Job",HttpStatus.NOT_FOUND);
		}
	}
	
	
	//===================================SHOWALL JOB===========================================================================
	
	
	@GetMapping(value="/showAllJob")
	public ResponseEntity<List<Job>> showAllJob()
	{
		List<Job> joblist=jobdao.displayJob();
		
		if(joblist.size()>0)
		{
			return new ResponseEntity<List<Job>>(joblist,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Job>>(joblist,HttpStatus.NOT_FOUND);
		}
	}
	
	//===================================DELETE JOB=============================================================================
	
	
	@GetMapping(value="/deleteJob/{jobid}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteJob(@PathVariable("jobid")int jobid)
	{
		Job job=jobdao.getJobDetails(jobid);
		
		if(jobdao.delete(job))
		{
			return new ResponseEntity<String>("Deleted Jod",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occuring While Deleting Job",HttpStatus.NOT_FOUND);
		}
	}
	
	
	//===================================GET JOB=================================================================================
	
	
	@GetMapping(value="/getJob/{jobid}")
	public ResponseEntity<Job> getJob(@PathVariable("jobid")int jobid)
	{
		Job job=jobdao.getJobDetails(jobid);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
		
}
