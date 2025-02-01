package com.parimal.async_and_blocking_task_scheduling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

@Configuration
public class ThreadConfig {

    @Bean
    public TaskScheduler getTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(4);
        taskScheduler.setThreadNamePrefix("CustomTask-");
        taskScheduler.initialize();
        return taskScheduler;
    }

    @Bean(name = "jobExecutor")       // used when used @Async, this annotation uses this executor to run concurrent jobs.
    public Executor getJobExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(10);
        taskExecutor.setThreadNamePrefix("NewTaskExec-");
        taskExecutor.initialize();
        return taskExecutor;
    }

}
