package com.codersyndrome.concurrency.basics.deadlock;

/**
 * @author Shirish Patil
 *
 */
public class TestDeadLock1 {

	public static void main(String[] args) {

		School school = new School();

		Thread t1 = new Thread(() -> {
			school.method1();
		}, "t1");

		Thread t2 = new Thread(() -> {
			school.method3();
		}, "t2");

		t1.start();
		t2.start();
	}

}
