/**
 * 
 */
package com.codersyndrome.concurrency.basics;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class TestNewThread {

	public static void main(String[] args) {
		TestNewThread _ins = new TestNewThread();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					_ins.printMessage();
				}
			}
		}, "Thread1");
		t1.setDaemon(true);

		Thread t2 = new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(1 / 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			_ins.printMessage();
		}, "Thread2");

		printProcessors();
		t2.start();
		t1.start();

	}

	public static void printProcessors() {
		String processors = String.format("Available processors: %s", Runtime.getRuntime().availableProcessors());
		System.out.println(processors);
	}

	public void printMessage() {
		String message = String.format("\nThread name: %s,\nid: %s,%nstate: %s,\npriority: %s",
				Thread.currentThread().getName(), Thread.currentThread().getId(), Thread.currentThread().getState(),
				Thread.currentThread().getPriority());

		System.out.println(message);
	}
}