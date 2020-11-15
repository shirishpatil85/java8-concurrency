package com.codersyndrome.concurrency.lock.readwritelock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.codersyndrome.concurrency.utilities.threadfactory.ThreadFactoryBuilder;

/**
 * @author Shirish Patil
 *
 */
public class TestDictionaryTask {

	public static void main(String[] args) {

		ThreadFactory threadFactory = new ThreadFactoryBuilder().setName("Thread").build();

		ExecutorService executorService = Executors.newFixedThreadPool(2, threadFactory);

		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

		DictionaryTask task = new DictionaryTask(lock.readLock(), lock.writeLock());

		Runnable writeTask = () -> {
			task.writeDefinitions();
		};

		Runnable readTask = () -> {
			task.readDefinitions();
		};

		executorService.submit(readTask);
		executorService.submit(writeTask);
	}
}