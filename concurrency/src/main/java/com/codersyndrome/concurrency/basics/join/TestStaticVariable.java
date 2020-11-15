package com.codersyndrome.concurrency.basics.join;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class TestStaticVariable {

	private static BigDecimal result;

	public static void main(String[] args) {

		System.out.println(String.format("%s begins ...", Thread.currentThread().getName()));
		long beginTime = System.currentTimeMillis();
		Thread t1 = new Thread(() -> {
			result = square(5);
		}, "t1");

		t1.start();

		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis() - beginTime;
		System.out.println(String.format("Result is %s in %s millis ", result, endTime));

	}

	private static BigDecimal square(int digits) {
		System.out.println(String.format("%s inside square()", Thread.currentThread().getName()));
		sleepThread();
		long beginTime = System.currentTimeMillis();
		BigDecimal sqr = new BigDecimal(digits * digits);
		long endTime = (System.currentTimeMillis() - beginTime);
		System.out.println(
				String.format("%s is exiting square() in %s millis ", Thread.currentThread().getName(), endTime));
		return sqr;
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
		System.out.println(String.format("%s has wokeup in %s seconds", Thread.currentThread().getName(), endTime));
	}

}
