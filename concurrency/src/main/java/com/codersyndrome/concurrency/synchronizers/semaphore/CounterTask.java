package com.codersyndrome.concurrency.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author Shirish Patil
 *
 */
public class CounterTask implements Runnable {

	private Semaphore semaphore;

	public CounterTask(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public void run() {

		acquire();

		performTask();

		release();

	}

	private void acquire() {
		System.out.println(String.format("%s permits available for thread %s", this.semaphore.availablePermits(),
				Thread.currentThread().getName()));
		try {
			this.semaphore.acquire();
			System.out.println(String.format("%s acquired lock. %s permits available now.",
					Thread.currentThread().getName(), this.semaphore.availablePermits()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void performTask() {
		for (int i = 0; i < 5; i++) {
			System.out.println(String.format("%s: count - %s", Thread.currentThread().getName(), i));
		}
	}

	private void release() {
		this.semaphore.release();
		System.out.println(String.format("%s released lock. %s permits available now.",
				Thread.currentThread().getName(), this.semaphore.availablePermits()));

	}
}