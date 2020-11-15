package com.codersyndrome.concurrency.synchronizers.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author Shirish Patil
 *
 */
public class TestExchanger {

	public static void main(String[] args) {

		Exchanger<String> exchanger = new Exchanger<>();

		Person p1 = new Person(exchanger, "Hi There!");
		Person p2 = new Person(exchanger, "I'm good.");

		Thread personOne = new Thread(p1, "p1");
		Thread personTwo = new Thread(p2, "p2");
		personOne.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {

		}

		personTwo.start();

	}

}
