package com.codersyndrome.concurrency.synchronizers.semaphore.binary;

import java.util.concurrent.Semaphore;

import com.codersyndrome.concurrency.synchronizers.semaphore.CounterTask;

/**
 * @author Shirish Patil
 *
 */
public class TestBinarySemaphore {

	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(1);

		CounterTask task = new CounterTask(semaphore);

		Thread t1 = new Thread(task, "T1");
		Thread t2 = new Thread(task, "T2");
		Thread t3 = new Thread(task, "T3");

		t1.start();
		t2.start();
		t3.start();

	}
}