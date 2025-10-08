package com.example.task.Service;

import com.example.task.DTO.TaskRequest;
import com.example.task.Model.Task;
import com.example.task.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;

    public Task createTask(String userName, TaskRequest req )
    {
        Task task = Task.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .dueDate(req.getDueDate())
                .completed(false)
                .userName(userName)
                .build();

        return taskRepository.save(task);

    }

    public List<Task> getTasks(String userName)
    {
        return taskRepository.findByUserName(userName);
    }

    public Task updateTask(Long id, String userName, TaskRequest req)
    {
        Task task = taskRepository.findById(id).orElseThrow();

        if(!task.getUserName().equals(userName))
        {
            throw new RuntimeException("userName not matched");
        }

        task.setTitle(req.getTitle());
        task.setDescription(req.getDescription());
        task.setDueDate(req.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id, String userName)
    {
        Task task = taskRepository.findById(id).orElseThrow();

        if(!task.getUserName().equals(userName))
        {
            throw new RuntimeException("userName not matched");
        }
        taskRepository.delete(task);
    }

    public Task markCompleted(Long id, String userName)
    {
        Task task = taskRepository.findById(id).orElseThrow();

        if(!task.getUserName().equals(userName))
        {
            throw new RuntimeException("userName not matched");
        }
        task.setCompleted(true);
        return taskRepository.save(task);
    }

}
