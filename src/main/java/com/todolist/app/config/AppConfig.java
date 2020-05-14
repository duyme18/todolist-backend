package com.todolist.app.config;

import com.todolist.app.service.TaskService;
import com.todolist.app.service.impl.TaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public TaskService taskService() {
        return new TaskServiceImpl();
    }
}
