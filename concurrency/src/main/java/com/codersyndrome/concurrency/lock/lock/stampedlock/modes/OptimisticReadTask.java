package com.codersyndrome.concurrency.lock.lock.stampedlock.modes;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Shirish Patil
 *
 */
public class OptimisticReadTask implements Runnable {

	private Square square;
	private StampedLock lock;

	public OptimisticReadTask(Square square, StampedLock lock) {
		this.square = square;
		this.lock = lock;
	}

	@Override
	public void run() {

		for (int i = 0; i < 50; i++) {

			long stamp = lock.tryOptimisticRead();

			if (lock.validate(stamp)) {
				System.out.println(String.format("Valid optimisticlock: stamp-%s, %s", stamp, square));
			} else {
				System.out.println(String.format("Not valid optimisticlock: stamp-%s, %s", stamp, square));
			}
			sleep();
		}
	}

	private void sleep() {
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
