package com.codersyndrome.concurrency.lock.lock.stampedlock.modes;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Shirish Patil
 *
 */
public class ReadingTask implements Runnable {

	private Square square;
	private StampedLock lock;

	public ReadingTask(Square square, StampedLock lock) {
		this.square = square;
		this.lock = lock;
	}

	@Override
	public void run() {

		for (int index = 0; index < 50; index++) {
			long stamp = lock.readLock();
			System.out.println(String.format("After acquiring readlock: stamp-%s, %s", stamp, square));

			sleep();

			lock.unlockRead(stamp);
			System.out.println(String.format("After releasing readlock: stamp-%s, %s", stamp, square));

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