package com.codersyndrome.concurrency.basics.threadlocal;

/**
 * @author Shirish Patil
 *
 */
public class TestInheritableThreadLocal {

	private enum ThreadName {
		Parent, Child;
	}

	// private static ThreadLocal<Integer> localVal = new ThreadLocal<>();
	private static ThreadLocal<Integer> localVal = new InheritableThreadLocal<>();

	public static void main(String[] args) {

		Runnable parentRunnable = () -> {

			localVal.set(10);

			Runnable childRunnable = () -> {
				System.out.println(String.format("%s with val: %s", Thread.currentThread().getName(), localVal.get()));
			};

			new Thread(childRunnable, ThreadName.Child.name()).start();

			System.out.println(String.format("%s with val: %s", Thread.currentThread().getName(), localVal.get()));

		};

		new Thread(parentRunnable, ThreadName.Parent.name()).start();
	}
}