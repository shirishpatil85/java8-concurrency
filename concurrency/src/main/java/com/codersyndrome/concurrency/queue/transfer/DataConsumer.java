package com.codersyndrome.concurrency.queue.transfer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * @author Shirish Patil
 *
 */
public class DataConsumer implements Runnable {

	private TransferQueue<String> dataQueue;

	public DataConsumer(TransferQueue<String> dataQueue) {
		this.dataQueue = dataQueue;
	}

	@Override
	public void run() {

		for (int i = 0; i < 5; i++) {
			try {
				String data = dataQueue.take();
				System.out.println("Data consumed - " + data);
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}