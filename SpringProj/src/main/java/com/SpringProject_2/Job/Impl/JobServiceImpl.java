package com.SpringProject_2.Job.Impl;

import com.SpringProject_2.Job.JobService;
import com.SpringProject_2.Job.job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<job> jobs = new ArrayList();
    private Long Id = 1L;

    @Override
    public List<job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(job Job) {
        Job.setId(Id++);
        jobs.add(Job);
    }

    @Override
    public job getJobById(Long id) {
        for(job Job: jobs )
        {
            if(Job.getId().equals(id))
            {
                return Job;
            }
        }
        return null;
    }
}
