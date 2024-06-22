package com.SpringProject_2.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {


    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<job> FindAll()
    {
        return jobService.findAll();
    }

    @PostMapping("/jobs")
    public ResponseEntity<String>createJob(@RequestBody job job)
    {
        jobService.createJob(job);
        return new ResponseEntity<>("job added :)",HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<job> getJobById(@PathVariable Long id)
    {
        job Job = jobService.getJobById(id);
        if(Job != null)
            return new ResponseEntity<>(Job,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
