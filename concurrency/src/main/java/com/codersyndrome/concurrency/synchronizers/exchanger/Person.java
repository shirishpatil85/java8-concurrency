/**
 * 
 */
package com.codersyndrome.concurrency.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author Shirish Patil
 *
 */
public class Person implements Runnable {

	private String message;
	private final Exchanger<String> exchanger;

	public Person(Exchanger<String> exchanger, String message) {
		this.exchanger = exchanger;
		this.message = message;
	}

	@Override
	public void run() {

		String received = null;
		System.out.println(String.format("%s is sending: %s", Thread.currentThread().getName(), this.message));
		try {
			received = exchanger.exchange(this.message);
			System.out.println(String.format("%s has received: %s", Thread.currentThread().getName(), received));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
