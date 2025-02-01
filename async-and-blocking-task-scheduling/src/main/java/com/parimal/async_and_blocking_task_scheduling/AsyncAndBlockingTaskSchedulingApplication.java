package com.parimal.async_and_blocking_task_scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.*;

@SpringBootApplication
@EnableScheduling				// for process scheduling.
@EnableAsync					// for async scheduling/processing.
@Slf4j
public class AsyncAndBlockingTaskSchedulingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AsyncAndBlockingTaskSchedulingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ThreadPoolExecutor content.
//		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//				4,
//				6,
//				2,
//				TimeUnit.SECONDS,
//				new LinkedBlockingQueue<>(10),
//				new RejectedExecutionHandler() {
//					@Override
//					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//						log.info("Thread rejected... Retrying...");
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//						executor.submit(r);
//                    }
//				}
//		);
//		log.info("Starting main Thread -> {}", Thread.currentThread().getName());
//		for(int x = 0; x < 100; x++) {
//			threadPoolExecutor.submit(new Task(Integer.toString(x)));
////			Thread.sleep(1000);
//		}
//		log.info("Ending main Thread -> {}", Thread.currentThread().getName());

		// ScheduledThreadPoolExecutor content.
//		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
//				6,
//				new ThreadFactory() {
//					@Override
//					public Thread newThread(Runnable r) {
//						return new Thread(r, "thread-" + System.nanoTime());
//					}
//				},
//				new RejectedExecutionHandler() {
//					@Override
//					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//						log.info("Thread rejected... Retrying...");
//						try {
//							Thread.sleep(2000);
//						} catch (InterruptedException e) {
//							throw new RuntimeException(e);
//						}
//						executor.submit(r);
//					}
//				}
//		);
//
//		scheduledThreadPoolExecutor.schedule(
//				new Task("Schedule task"),
//				4,
//				TimeUnit.SECONDS
//		);

	}
}
