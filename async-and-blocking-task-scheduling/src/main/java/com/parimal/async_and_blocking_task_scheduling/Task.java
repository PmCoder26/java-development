package com.parimal.async_and_blocking_task_scheduling;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Task implements Runnable {

    private final String command;

    @Override
    public void run() {

        log.info("{} Running task... {}", command, Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e );
        }
        log.info("{} Ending task... {}", command, Thread.currentThread().getName());
    }
}
