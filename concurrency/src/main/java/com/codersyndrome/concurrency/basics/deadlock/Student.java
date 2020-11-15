package com.codersyndrome.concurrency.basics.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class Student {

	Object obj1 = new Object();
	Object obj2 = new Object();

	public void printScore() {
		synchronized (obj1) {
			sleepThread();
			synchronized (obj2) {
				printResults();
				System.out.println("Score: " + 100);
			}
		}

	}

	public void printResults() {
		synchronized (obj2) {
			sleepThread();
			synchronized (obj1) {
				printScore();
				System.out.println("Result: " + 100);
			}
		}
	}

	private static void sleepThread() {
		System.out.println(String.format("%s is sleeping", Thread.currentThread().getName()));
		long beginTime = System.currentTimeMillis();
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " :: interrupted");
		}
		long endTime = (System.currentTimeMillis() - beginTime) / 1000;
		System.out.println(String.format("%s is wokeup in %s seconds", Thread.currentThread().getName(), endTime));
	}

}
