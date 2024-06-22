package com.SpringProject_2.Job;

import java.util.List;

public interface JobService {
    List<job> findAll();
    void createJob(job Job);

    job getJobById(Long id);
}
