package com.codersyndrome.concurrency.queue.synchronous;

import java.util.UUID;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Shirish Patil
 *
 */
public class DataProducer implements Runnable {

	private SynchronousQueue<String> dataQueue;

	public DataProducer(SynchronousQueue<String> dataQueue) {
		this.dataQueue = dataQueue;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			String data = UUID.randomUUID().toString();
			System.out.println("Producing data-" + data);
			try {
				dataQueue.put(data);
				;
				// TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}