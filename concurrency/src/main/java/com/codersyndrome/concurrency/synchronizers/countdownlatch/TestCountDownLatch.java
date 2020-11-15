package com.codersyndrome.concurrency.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class TestCountDownLatch {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch beginningLatch = new CountDownLatch(1);
		CountDownLatch finishingLatch = new CountDownLatch(3);

		ExecutorService executorService = Executors.newFixedThreadPool(3);

		for (int index = 0; index < 3; index++) {
			executorService.execute(new CustomThread(beginningLatch, finishingLatch));
		}

		System.out.println(String.format("%s : in progress...", Thread.currentThread().getName()));
		beginningLatch.countDown();

		TimeUnit.SECONDS.sleep(2);

		System.out.println(String.format("%s : still progressing...", Thread.currentThread().getName()));

		finishingLatch.await();

		executorService.shutdown();

		System.out.println(String.format("%s : Finished work.", Thread.currentThread().getName()));

	}
}
