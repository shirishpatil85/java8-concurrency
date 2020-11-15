package com.codersyndrome.concurrency.synchronizers.phaser;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class RunnableTask implements Runnable {

	private Phaser phaser;

	public RunnableTask(Phaser phaser) {
		this.phaser = phaser;
		this.phaser.register();
	}

	@Override
	public void run() {
		print("After register");
		for (int i = 0; i < 2; i++) {
			sleep();
			print("Before await " + i + ":");
			this.phaser.arriveAndAwaitAdvance();
			print("After advance " + i + ":");
		}
	}

	private void sleep() {
		try {
			Random r = new Random();
			int sleepTime = r.nextInt(10);
			System.out.println(
					String.format("%s moving to sleep for %s seconds.", Thread.currentThread().getName(), sleepTime));
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void print(String msg) {

		System.out.println(String.format("%s: %s, time=%s, registered=%s, arrived=%s, unarrived=%s, phase=%s.", msg,
				Thread.currentThread().getName(), LocalTime.now(), this.phaser.getRegisteredParties(),
				this.phaser.getArrivedParties(), this.phaser.getUnarrivedParties(), this.phaser.getPhase()));
	}

}
