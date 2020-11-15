package com.codersyndrome.concurrency.lock.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author Shirish Patil
 *
 */
public class ResourceWithLockTwice implements Runnable {

	private Lock lock;

	public ResourceWithLockTwice(Lock lock) {
		this.lock = lock;
	}

	private void printDetails() {
		try {

			lock.lock();
			System.out.println(Thread.currentThread().getName() + " Lock aquired.");
			lock.lock();
			System.out.println(Thread.currentThread().getName() + " Lock aquired.");
		} finally {
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + " Lock is released.");
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + " Lock is released.");
		}

		if (lock.tryLock()) {
			try {
				System.out.println(Thread.currentThread().getName() + " Lock aquired one more time.");
			} finally {
				lock.unlock();
				System.out.println(Thread.currentThread().getName() + " Lock is released.");
				// lock.unlock();
				// System.out.println(Thread.currentThread().getName() + " Lock is released.");
			}
		} else {
			System.out.println(Thread.currentThread().getName() + " Lock is unavailable.");
		}
	}

	@Override
	public void run() {
		printDetails();
	}
}
