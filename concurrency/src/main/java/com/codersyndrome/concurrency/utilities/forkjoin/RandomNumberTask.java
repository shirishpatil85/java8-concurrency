package com.codersyndrome.concurrency.utilities.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Shirish Patil
 *
 */
public class RandomNumberTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;

	private long limit = 0;

	public RandomNumberTask(int limit) {
		this.limit = limit;
	}

	@Override
	protected Long compute() {

		long result = 0;

		if (this.limit == 0) {
			return this.limit;
		}

		if (this.limit == 1) {
			return getRandomNumber();
		}

		List<ForkJoinTask<Long>> tasks = new ArrayList<>();
		for (int index = 0; index < limit; index++) {
			RandomNumberTask task = new RandomNumberTask(1);
			task.fork();
			tasks.add(task);
		}

		for (ForkJoinTask<Long> subTask : tasks) {
			result = result + subTask.join();
		}

		return result;
	}

	private long getRandomNumber() {
		long number = new Random().nextInt(100) + 1;
		System.out.println("Generated Number- " + number);
		return number;
	}

}
