package com.codersyndrome.concurrency.lock.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @author Shirish Patil
 *
 */
public class DictionaryTask {

	private enum Dictionary {

		TRIVIAL("Little Value"), JEOPARDY("Failure"), MORALE("Confidence");

		private String definition;

		Dictionary(String definition) {
			this.definition = definition;
		}

		public String getDefinition() {
			return this.definition;
		}
	}

	private ReadLock readLock;

	private WriteLock writeLock;

	private Map<String, String> wordSearch = new HashMap<>(0);

	public DictionaryTask(ReadLock readLock, WriteLock writeLock) {
		this.readLock = readLock;
		this.writeLock = writeLock;
	}

	public void writeDefinitions() {
		Dictionary[] values = Dictionary.values();

		for (Dictionary dictionary : values) {
			sleep();
			writeLock.lock();
			try {
				printDetails("Writing", dictionary.name(), dictionary.getDefinition());
				wordSearch.put(dictionary.name(), dictionary.getDefinition());

			} finally {
				writeLock.unlock();
			}
		}
	}

	public void readDefinitions() {
		Dictionary[] values = Dictionary.values();
		int length = values.length;

		while (true) {

			readLock.lock();
			try {
				int position = new Random().nextInt(length);
				Dictionary dictionary = values[position];
				String definition = wordSearch.get(dictionary.name());
				printDetails("reading", dictionary.name(), definition);
			} finally {
				readLock.unlock();
			}
		}
	}

	private void sleep() {
		System.out.println("Write thread sleeping...");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printDetails(String action, String word, String definition) {
		if (definition == null || definition.isEmpty()) {
			return;
		}
		System.out.println(String.format("%s is %s: word [%s], definition [%s]", Thread.currentThread().getName(),
				action, word, definition));
	}
}
