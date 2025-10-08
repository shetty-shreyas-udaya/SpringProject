package com.example.task.Controller;


import com.example.task.DTO.TaskRequest;
import com.example.task.Model.Task;
import com.example.task.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController
{
    private final TaskService taskService;

    private String extractUsername()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth == null) ? null: auth.getName();
    }

    @PostMapping
    public Task creatTask(@RequestBody TaskRequest taskRequest)
    {
        return taskService.createTask(extractUsername(),taskRequest);
    }

    @GetMapping
    public List<Task> getTask()
    {
        return taskService.getTasks(extractUsername());
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody TaskRequest taskRequest)
    {
        return taskService.updateTask(id,extractUsername(),taskRequest);
    }

    @PatchMapping("{id}/completed")
    public Task completedTask(@PathVariable Long id)
    {
        return taskService.markCompleted(id,extractUsername());
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id,extractUsername());
    }

}
