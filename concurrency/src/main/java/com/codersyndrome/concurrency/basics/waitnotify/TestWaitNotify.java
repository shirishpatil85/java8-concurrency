package com.codersyndrome.concurrency.basics.waitnotify;

import java.util.Arrays;
import java.util.List;

/**
 * @author Shirish Patil
 *
 */
public class TestWaitNotify {

	public static void main(String[] args) {

		SharedObject sharedObject = new SharedObject();

		List<String> fruitsList = Arrays.asList("Apple", "Banana", "Orange");
		int listSize = fruitsList.size();

		Thread producer = new Thread(() -> {
			System.out.println("producer thread started");
			fruitsList.forEach(p -> {
				sharedObject.writeData(p);
			});
		}, "producer");

		Thread consumer = new Thread(() -> {
			System.out.println("consumer thread started");
			for (int i = 0; i < listSize; i++) {
				sharedObject.readData();
			}
		}, "consumer");

		consumer.start();
		producer.start();

	}
}
