package com.codersyndrome.concurrency.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Function;

import com.codersyndrome.concurrency.utilities.threadfactory.ThreadFactoryBuilder;

/**
 * @author Shirish Patil
 *
 */
public class TestCompletableFuture {

	public static void main(String[] args) {

		ThreadFactory threadFactory = new ThreadFactoryBuilder().setName("Thead").build();
		ExecutorService executor = Executors.newFixedThreadPool(1, threadFactory);
		CompletableFuture<Void> runCf = CompletableFuture.runAsync(
				() -> System.out.println(Thread.currentThread().getName() + "-This is inside a thread"), executor);

		CompletableFuture<Void> fruitNameCf = CompletableFuture
				.supplyAsync(() -> Arrays.asList("Banana", "Orange", "Apple"))
				.thenApplyAsync(l -> l.stream().filter(a -> a.equalsIgnoreCase("Orange")).findFirst(), executor)
				.thenAcceptAsync(t -> System.out.println(Thread.currentThread().getName() + "-" + t.get()), executor);

		CompletableFuture<Integer> multiplication = CompletableFuture.supplyAsync(() -> 3 * 3 * 3);
		CompletableFuture<Integer> addition = CompletableFuture.supplyAsync(() -> 3 + 3 + 3);
		multiplication.thenAcceptBothAsync(addition,
				(a, b) -> System.out.println(String.format("a-%s, b-%s, a+b-%s", a, b, a + b)));

		Function<List<Integer>, CompletionStage<Integer>> aFunction = a -> CompletableFuture
				.supplyAsync(() -> a.stream().max(Integer::compareTo).get());

		CompletableFuture<Void> maxCf = CompletableFuture.supplyAsync(() -> Arrays.asList(1, 20, 3))
				.thenComposeAsync(aFunction).thenAccept(a -> System.out.println("The max number is-" + a));

		executor.shutdown();
		try {
			runCf.join();
			System.out.println("fruitNameCf.get() = " + fruitNameCf.get());
			System.out.println("addition.get() = " + addition.get());
			multiplication.get();
			System.out.println("maxCf.get() = " + maxCf.get());
		} catch (Exception ex) {
			System.out.println("Exception occurred " + ex.getMessage());
		}
	}
}