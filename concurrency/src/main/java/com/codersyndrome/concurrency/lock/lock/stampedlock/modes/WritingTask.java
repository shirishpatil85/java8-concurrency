package com.codersyndrome.concurrency.lock.lock.stampedlock.modes;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Shirish Patil
 *
 */
public class WritingTask implements Runnable {

	private Square square;
	private StampedLock lock;

	public WritingTask(Square square, StampedLock lock) {
		this.square = square;
		this.lock = lock;
	}

	@Override
	public void run() {

		for (int index = 0; index < 10; index++) {
			long stamp = this.lock.writeLock();
			System.out.println(String.format("After acquiring writelock: stamp-%s, %s", stamp, square));

			this.square.set(index, index + 1);
			sleep();

			this.lock.unlockWrite(stamp);
			System.out.println(String.format("After releasing writelock: stamp-%s, %s", stamp, square));

			sleep();
		}
	}

	private void sleep() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}