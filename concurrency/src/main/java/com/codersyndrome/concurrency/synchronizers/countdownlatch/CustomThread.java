package com.codersyndrome.concurrency.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class CustomThread implements Runnable {

	private CountDownLatch beginningLatch;

	private CountDownLatch finishingLatch;

	public CustomThread(CountDownLatch beginningLatch, CountDownLatch finishingLatch) {

		this.beginningLatch = beginningLatch;
		this.finishingLatch = finishingLatch;

	}

	@Override
	public void run() {

		printMessage("Started");
		await();
		printMessage("doing work");
		sleep();
		finishingLatch.countDown();
		printMessage("Finished");

	}

	private void printMessage(String msg) {
		System.out.println(
				String.format("%s :: %s - %s", System.currentTimeMillis(), Thread.currentThread().getName(), msg));
	}

	private void await() {
		try {
			beginningLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sleep() {

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
