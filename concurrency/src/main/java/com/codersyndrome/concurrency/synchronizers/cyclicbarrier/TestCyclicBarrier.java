package com.codersyndrome.concurrency.synchronizers.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shirish Patil
 *
 */
public class TestCyclicBarrier {

	public static void main(String[] args) {

		int[] params = new int[] { 2, 4 };
		Computation comp1 = new Computation(params[0]);
		Computation comp2 = new Computation(params[1]);

		Runnable barrierAction = () -> {
			int result = comp1.getResult() + comp2.getResult();
			System.out.println(String.format("The sum of square root of %s and square root of %s is : %s", params[0],
					params[1], result));
		};

		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, barrierAction);

		comp1.setBarrier(cyclicBarrier);
		comp2.setBarrier(cyclicBarrier);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(comp1);
		executor.submit(comp2);

		try {
			System.out.println(String.format("%s is waiting for other threads", Thread.currentThread().getName()));
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
		System.out.println(String.format("%s is Finsihed.", Thread.currentThread().getName()));
	}

}
