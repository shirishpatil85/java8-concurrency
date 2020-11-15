package com.codersyndrome.concurrency.lock.lock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.codersyndrome.concurrency.utilities.threadfactory.ThreadFactoryBuilder;

/**
 * @author Shirish Patil
 *
 */
public class TestReentrantLock {

	public static void main(String[] args) {

		// testReadWriteTask();
		// testLockTwice_singleThread();
		testLockTwice_multiThread();

	}

	public static void testReadWriteTask() {
		List<String> fruits = Arrays.asList("BANANA", "APPLE", "ORANGE");

		ReadWriteTask task = new ReadWriteTask();

		ThreadFactory threadFactory = new ThreadFactoryBuilder().setName("Thread").build();

		ExecutorService executorService = Executors.newFixedThreadPool(2, threadFactory);

		Runnable writeTask = () -> fruits.forEach(a -> {
			task.writeData(a);
		});

		Runnable readTask = () -> {
			int size = fruits.size();
			for (int index = 0; index < size; index++) {
				task.readData();
			}
		};

		executorService.submit(readTask);
		executorService.submit(writeTask);
		executorService.shutdown();
	}

	public static void testLockTwice_singleThread() {
		System.out.println("**** SINGLETHREAD Test *******");

		Lock lock = new ReentrantLock();
		ResourceWithLockTwice resource = new ResourceWithLockTwice(lock);

		ExecutorService executorService = Executors.newFixedThreadPool(1);

		executorService.submit(resource);

		executorService.shutdown();

	}

	public static void testLockTwice_multiThread() {
		System.out.println("**** MULTITHREAD Test *******");
		Lock lock = new ReentrantLock();
		ResourceWithLockTwice resource = new ResourceWithLockTwice(lock);

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		executorService.submit(resource);
		executorService.submit(resource);

		executorService.shutdown();
	}
}
