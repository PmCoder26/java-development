package com.parimal.async_and_blocking_task_scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// Note - as we have two classes of Schedulers, unless you use the concurrency or the Async concept
//        the order of execution will be: all the methods of MyScheduler(logMe then logMe2) and
//        MyScheduler2(logMe and logMe2) will execute sequentially.

@Component
@Slf4j
public class MyScheduler {

    // It re-executes the method after the given time interval, not bothered about whether the method may take more time or not.
    @Scheduled(fixedRate = 1000)
    void logMe() {
//        log.info("Logged after every 1000ms from MyScheduler");
        log.info("Class-1, Scheduler-1 started: {}", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Class-1, Scheduler-1 ended: {}", Thread.currentThread().getName());
    }

    // It delays the given time interval after when the method is executed
    @Scheduled(fixedDelay = 2000)
    void logMe2() {
        log.info("Class-1, Scheduler-2 started: {}", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Class-1, Scheduler-2 ended: {}", Thread.currentThread().getName());
    }

//    initial delay: only used when the first time this method starts.
//    adds the initial delay before the first execution of the method/task
    @Scheduled(fixedDelay = 2000, initialDelay = 5000)
    @Async(value = "jobExecutor")      // run on new thread.
                // using @Async, it uses different thread pool rather than of TaskScheduler whose we created a bean.
                // that another thread pool may contain up to 10 threads.
    void logMe3() {
        log.info("Class-1, Scheduler-3 started: {}", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Class-1, Scheduler-3 ended: {}", Thread.currentThread().getName());
    }

    @Scheduled(cron = "*/5 * * * * *")
    void logMe4() {
        log.info("Class-1, Scheduler-4 started: {}", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Class-1, Scheduler-4 ended: {}", Thread.currentThread().getName());
    }

}

//@Component
//@Slf4j
//class MyScheduler2 {
//
//    @Scheduled(fixedRate = 1000)
//    void logMe() {
////        log.info("Logged after every 1000ms from MyScheduler");
//        log.info("Class-2, Scheduler-1 started: {}", Thread.currentThread().getName());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("Class-2, Scheduler-1 ended: {}", Thread.currentThread().getName());
//    }
//
//    @Scheduled(fixedRate = 1000)
//    void logMe2() {
//        log.info("Class-2, Scheduler-2 started: {}", Thread.currentThread().getName());
//        try {
//            Thread.sleep(2000);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        log.info("Class-2, Scheduler-2 ended: {}", Thread.currentThread().getName());
//    }
//
//}
