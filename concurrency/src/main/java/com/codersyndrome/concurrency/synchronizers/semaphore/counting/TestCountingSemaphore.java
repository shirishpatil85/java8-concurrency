package com.codersyndrome.concurrency.synchronizers.semaphore.counting;

import java.util.concurrent.Semaphore;

import com.codersyndrome.concurrency.synchronizers.semaphore.CounterTask;

/**
 * @author Shirish Patil
 *
 */
public class TestCountingSemaphore {

	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(2, true);

		CounterTask task = new CounterTask(semaphore);

		Thread t1 = new Thread(task, "T1");
		Thread t2 = new Thread(task, "T2");
		Thread t3 = new Thread(task, "T3");
		Thread t4 = new Thread(task, "T4");
		Thread t5 = new Thread(task, "T5");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

	}

}
