package com.codersyndrome.concurrency.utilities.executors;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import com.codersyndrome.concurrency.utilities.threadfactory.ThreadFactoryBuilder;

/**
 * @author Shirish Patil
 *
 */
public class TestExecutorService {

	public static void main(String[] args) {

		ThreadFactory threadFactory = new ThreadFactoryBuilder().setName("Thread").setDaemon(false).build();

		ExecutorService executorService = Executors.newFixedThreadPool(1, threadFactory);

		Callable<BigDecimal> myCallable = new MyCallable();

		Future<BigDecimal> result = executorService.submit(myCallable);

		while (!result.isDone()) {
			System.out.println("Waiting....");
		}

		try {
			System.out.println(String.format("Result is: %s", result.get()));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
	}
}