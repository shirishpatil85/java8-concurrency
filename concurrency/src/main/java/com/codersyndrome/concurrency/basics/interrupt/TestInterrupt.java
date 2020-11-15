package com.codersyndrome.concurrency.basics.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class TestInterrupt {

	public static void main(String[] args) {

		Runnable r = () -> {
			System.out.println(String.format("%s is sleeping- %s", Thread.currentThread().getName(),
					Thread.currentThread().isInterrupted()));
			try {
				while (true) {
					TimeUnit.MILLISECONDS.sleep(2);
				}
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " :: interrupted");
			}

			System.out.println(String.format("%s is interrupted- %s", Thread.currentThread(),
					Thread.currentThread().isInterrupted()));
		};

		Thread t = new Thread(r, "T1");
		t.start();

		sleeping(1);

		t.interrupt();

		System.out.println(String.format("%s interrupted- %s", t.getName(), Thread.interrupted()));
	}

	public static void sleeping(long time) {
		System.out.println(String.format("%s is sleeping.", Thread.currentThread().getName()));
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage() + ":: interrupted");
		}
	}
}