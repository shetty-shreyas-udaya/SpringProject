package com.SpringProject_2.Job;

public class job {
    private Long id;
    private String title;
    private String Discription;
    private String Location;
    private String MinSalary;
    private String MaxSalary;

    public job(Long id, String title, String discription, String location, String minSalary, String maxSalary) {
        this.id = id;
        this.title = title;
        Discription = discription;
        Location = location;
        MinSalary = minSalary;
        MaxSalary = maxSalary;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDiscription() {
        return Discription;
    }

    public String getLocation() {
        return Location;
    }

    public String getMinSalary() {
        return MinSalary;
    }

    public String getMaxSalary() {
        return MaxSalary;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
