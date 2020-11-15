package com.codersyndrome.concurrency.utilities.threadfactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shirish Patil
 *
 */
public class ThreadFactoryBuilder {

	private String name;

	private int priority = Thread.NORM_PRIORITY;

	private boolean isDaemon = false;

	public ThreadFactoryBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ThreadFactoryBuilder setPriority(int priority) {
		this.priority = priority;
		return this;
	}

	public ThreadFactoryBuilder setDaemon(boolean isDaemon) {
		this.isDaemon = isDaemon;
		return this;
	}

	public ThreadFactory build() {
		AtomicLong count = new AtomicLong(0);
		return new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r, name + "-" + count.getAndIncrement());
				t.setPriority(priority);
				t.setDaemon(isDaemon);
				return t;
			}
		};

	}

}
