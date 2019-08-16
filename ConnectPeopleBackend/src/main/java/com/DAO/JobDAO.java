package com.DAO;

import java.util.List;

import com.WeChat.Job;

public interface JobDAO {
 public boolean addJob(Job job);
 public boolean delete(Job job);
 public List<Job> displayJob();
 public Job getJobDetails(int jobid);
}
