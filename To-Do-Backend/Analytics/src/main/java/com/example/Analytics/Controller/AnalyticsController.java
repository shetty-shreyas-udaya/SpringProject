package com.example.Analytics.Controller;

import com.example.Analytics.Service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    @Autowired
    private  AnalyticsService analyticsService;

    @GetMapping("/me/summary")
    public Map<String,Object> getUserTaskSummary(@RequestHeader ("Authorization") String token)
    {
        return analyticsService.getUserTaskSummary(token);
    }
}
