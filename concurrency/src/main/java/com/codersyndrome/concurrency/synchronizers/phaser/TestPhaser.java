package com.codersyndrome.concurrency.synchronizers.phaser;

import java.util.concurrent.Phaser;

/**
 * @author Shirish Patil
 *
 */
public class TestPhaser {

	public static void main(String[] args) {

		Phaser phaser = new Phaser();

		Thread t1 = new Thread(new RunnableTask(phaser), "t1");
		Thread t2 = new Thread(new RunnableTask(phaser), "t2");
		Thread t3 = new Thread(new RunnableTask(phaser), "t3");

		t1.start();
		t2.start();
		t3.start();

	}

}
