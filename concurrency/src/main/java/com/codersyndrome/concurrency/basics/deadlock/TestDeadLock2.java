package com.codersyndrome.concurrency.basics.deadlock;

/**
 * @author Shirish Patil
 *
 */
public class TestDeadLock2 {

	public static void main(String[] args) {

		Student student = new Student();

		Thread t1 = new Thread(() -> student.printScore(), "t1");

		Thread t2 = new Thread(() -> student.printResults(), "t2");

		t1.start();
		t2.start();
	}

}
