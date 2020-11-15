package com.codersyndrome.concurrency.queue.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Shirish Patil
 *
 */
public class TestBlockingQueue {

	public static void main(String[] args) {

		BlockingQueue<String> dataQueue = new PriorityBlockingQueue<>();

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(new DataProducer(dataQueue));
		executorService.submit(new DataConsumer(dataQueue));

		executorService.shutdown();
	}

}
