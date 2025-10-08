package com.example.Analytics.DTO;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String dueDate;
    private boolean completed;
    private String userName;
}
