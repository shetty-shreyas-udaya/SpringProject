package com.example.Analytics.Service;

import com.example.Analytics.Client.TaskClient;
import com.example.Analytics.DTO.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {
    @Autowired
    private  TaskClient taskClient;

    public Map<String,Object> getUserTaskSummary(String token)
    {
        List<TaskDto> tasks = taskClient.getTasksForUser(token);
        long total = tasks.size();
        long completed = tasks.stream().filter(TaskDto::isCompleted).count();
        long pending = total - completed;
        double completedRatio = (total == 0) ? 0.0 : (completed * 100.0 /total);
        Map<String,Object> summary = new HashMap<>();
        summary.put("total",total);
        summary.put("completed",completed);
        summary.put("pending",pending);
        summary.put("ratio",completedRatio);
        return summary;

    }


}
