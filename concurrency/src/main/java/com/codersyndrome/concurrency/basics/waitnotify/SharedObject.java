package com.codersyndrome.concurrency.basics.waitnotify;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Shirish Patil
 *
 */
public class SharedObject {

	private Queue<String> dataObject;

	private final Object objLock = new Object();

	public SharedObject() {
		dataObject = new PriorityQueue<String>(1);
	}

	public void writeData(String data) {
		synchronized (objLock) {
			System.out.println("producer has acquired lock.");
			while (!dataObject.isEmpty()) {
				System.out.println("Sleeping in producer");
				try {
					objLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			dataObject.offer(data);
			System.out.println(String.format("%s : %s", Thread.currentThread().getName(), data));
			objLock.notify();
			System.out.println("producer has released lock.");
		}
	}

	public String readData() {
		String result = null;
		synchronized (objLock) {
			System.out.println("consumer has acquired lock.");
			while (dataObject.isEmpty()) {
				System.out.println("Sleeping in consumer");
				try {
					objLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			result = dataObject.poll();
			System.out.println(String.format("%s : %s", Thread.currentThread().getName(), result));
			objLock.notify();
			System.out.println("consumer has released lock.");
		}
		return result;
	}
}
