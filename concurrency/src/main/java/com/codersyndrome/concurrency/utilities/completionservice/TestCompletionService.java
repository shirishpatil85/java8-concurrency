package com.codersyndrome.concurrency.utilities.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class TestCompletionService {

	public static void main(String[] args) {

		Callable<String> r1 = () -> {
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "This is long running task";
		};

		Callable<String> r2 = () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "This is quickly finishing task";
		};

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

		completionService.submit(r2);
		completionService.submit(r1);
		completionService.submit(r2);

		for (int i = 0; i < 10; i++) {
			try {
				Future<String> futureResult = completionService.poll(1, TimeUnit.SECONDS);
				if (futureResult != null && futureResult.isDone()) {
					System.out.println(futureResult.get());
				} else {
					System.out.println("no result");
				}
			} catch (Exception e) {
			}
		}

		executorService.shutdown();
	}
}