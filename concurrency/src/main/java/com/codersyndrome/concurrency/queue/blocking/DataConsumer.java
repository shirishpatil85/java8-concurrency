package com.codersyndrome.concurrency.queue.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class DataConsumer implements Runnable {

	private BlockingQueue<String> dataQueue;

	public DataConsumer(BlockingQueue<String> dataQueue) {
		this.dataQueue = dataQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 10; i++) {
			String data = null;
			try {
				data = dataQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Data consumed - " + data);
			sleep();
		}
	}

	private void sleep() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
