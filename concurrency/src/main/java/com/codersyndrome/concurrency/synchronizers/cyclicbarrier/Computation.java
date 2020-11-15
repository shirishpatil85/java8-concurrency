package com.codersyndrome.concurrency.synchronizers.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Shirish Patil
 *
 */
public class Computation implements Runnable {

	private int param = 0;

	private int result = 0;

	private CyclicBarrier cyclicBarrier;

	public Computation(int param) {
		if (!(param > 1)) {
			throw new NumberFormatException("Not a valid param");
		}
		this.param = param;
	}

	public void setBarrier(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		result = param * param;
		System.out.println(String.format("%s has finished work", Thread.currentThread().getName()));
		awaitBarrier();
		System.out.println(String.format("%s is Finsihed.", Thread.currentThread().getName()));
	}

	public int getResult() {
		return result;
	}

	private void awaitBarrier() {
		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}