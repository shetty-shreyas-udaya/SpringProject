package com.example.Analytics.Client;

import com.example.Analytics.DTO.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "Task-service", url = "${task-service-url}")
public interface TaskClient {
    @GetMapping("/tasks")
    List<TaskDto> getTasksForUser(@RequestHeader("Authorization") String token);
}
