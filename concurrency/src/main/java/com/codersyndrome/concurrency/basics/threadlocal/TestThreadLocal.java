package com.codersyndrome.concurrency.basics.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class TestThreadLocal {
	private enum ThreadName {
		T1, T2;
	}

	private static final ThreadLocal<String> localObject = new ThreadLocal<>();

	public static void main(String[] args) {

		Runnable r = () -> {

			String threadName = Thread.currentThread().getName();
			String value = null;
			if (threadName.equalsIgnoreCase("t1")) {
				value = "First Thread";
			} else if (threadName.equalsIgnoreCase("t2")) {
				value = "Second Thread";
			}

			localObject.set(value);
			sleeping(2);
			System.out.println(String.format("%s :: ThreadLocal - %s", threadName, localObject.get()));
		};

		Thread t1 = new Thread(r, ThreadName.T1.name());
		Thread t2 = new Thread(r, ThreadName.T2.name());

		t1.start();
		t2.start();

	}

	public static void sleeping(long time) {
		System.out.println(String.format("%s is sleeping.", Thread.currentThread().getName()));
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage() + ":: interrupted");
		}
		System.out.println(String.format("%s is wokeup.", Thread.currentThread().getName()));
	}

}
