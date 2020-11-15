package com.codersyndrome.concurrency.basics.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Shirish Patil
 *
 */
public class TimerDemo {

	public static void main(String[] args) {

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Alarm going off.");
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 2000);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();

	}

}
