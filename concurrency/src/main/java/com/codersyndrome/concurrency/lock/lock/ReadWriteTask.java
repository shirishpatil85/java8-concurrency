package com.codersyndrome.concurrency.lock.lock;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shirish Patil
 *
 */
public class ReadWriteTask {

	private Queue<String> dataQueue = new PriorityQueue<>(1);

	private Lock lock = new ReentrantLock();

	private Condition condition = lock.newCondition();

	public void writeData(String data) {

		lock.lock();

		printDetails("has acquired lock.");
		try {
			while (!dataQueue.isEmpty()) {
				await();
			}
			printDetails("is writing " + data);
			dataQueue.offer(data);
			signal();
		} finally {
			printDetails("has released lock.");
			lock.unlock();
		}
	}

	public String readData() {

		lock.lock();

		String data = null;
		printDetails("has acquired lock.");
		try {
			while (dataQueue.isEmpty()) {
				await();
			}
			data = dataQueue.poll();
			printDetails("is reading " + data);
			signal();
			return data;
		} finally {
			printDetails("has released lock.");
			lock.unlock();
		}
	}

	private void await() {
		printDetails("is waiting");
		try {
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void signal() {
		condition.signal();
	}

	private void printDetails(String message) {
		System.out.println(String.format("%s %s", Thread.currentThread().getName(), message));
	}
}