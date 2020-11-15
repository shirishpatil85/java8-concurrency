package com.codersyndrome.concurrency.lock.lock.stampedlock.modes;

/**
 * @author Shirish Patil
 *
 */
public class Square {

	private int x;

	private int y;

	public Square() {
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Square [x=" + x + ", y=" + y + "]";
	}

}