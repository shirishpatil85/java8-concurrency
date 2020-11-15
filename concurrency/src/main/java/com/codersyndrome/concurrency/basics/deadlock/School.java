/**
 * 
 */
package com.codersyndrome.concurrency.basics.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class School {

	private Object obj1 = new Object();
	private Object obj2 = new Object();

	public void method1() {
		synchronized (obj1) {
			sleepThread();
			synchronized (obj2) {
				method2();
			}
		}

	}

	public void method2() {
		System.out.println("Inside method2");

	}

	public void method3() {

		synchronized (obj2) {
			sleepThread();
			synchronized (obj1) {
				method4();
			}
		}

	}

	public void method4() {
		System.out.println("Inside method4");

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
