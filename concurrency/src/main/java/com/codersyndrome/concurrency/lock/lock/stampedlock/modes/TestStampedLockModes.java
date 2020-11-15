package com.codersyndrome.concurrency.lock.lock.stampedlock.modes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.StampedLock;

import com.codersyndrome.concurrency.utilities.threadfactory.ThreadFactoryBuilder;

/**
 * @author Shirish Patil
 *
 */
public class TestStampedLockModes {

	public static void main(String[] args) {

		Square square = new Square();
		StampedLock lock = new StampedLock();

		ThreadFactory threadFactory = new ThreadFactoryBuilder().setName("Thread").build();

		ExecutorService executorService = Executors.newFixedThreadPool(3, threadFactory);

		Thread reading = new Thread(new ReadingTask(square, lock), "read");
		Thread writing = new Thread(new WritingTask(square, lock), "write");
		Thread optimisticReading = new Thread(new OptimisticReadTask(square, lock), "optimistc");
		executorService.submit(reading);
		executorService.submit(writing);
		executorService.submit(optimisticReading);

	}

}
