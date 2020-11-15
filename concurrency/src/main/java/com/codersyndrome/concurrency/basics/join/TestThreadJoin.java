package com.codersyndrome.concurrency.basics.join;

import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class TestThreadJoin {

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			sleepThread(10);
		}, "t1");
		Thread t2 = new Thread(() -> {
			sleepThread(15);
		}, "t2");
		Thread t3 = new Thread(() -> {
			sleepThread(20);
		}, "t3");

		t1.start();
		t2.start();
		t3.start();

		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void sleepThread(int duration) {
		System.out.println(String.format("%s is sleeping", Thread.currentThread().getName()));
		long beginTime = System.currentTimeMillis();
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " :: interrupted");
		}
		long endTime = (System.currentTimeMillis() - beginTime) / 1000;
		System.out.println(String.format("%s is wokeup in %s seconds", Thread.currentThread().getName(), endTime));
	}

}
