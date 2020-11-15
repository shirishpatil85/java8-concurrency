package com.codersyndrome.concurrency.utilities.executors;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

/**
 * @author Shirish Patil
 *
 */
public class MyCallable implements Callable<BigDecimal> {

	private final static int LASTITER = 17;

	@Override
	public BigDecimal call() throws Exception {
		MathContext mc = new MathContext(100, RoundingMode.HALF_UP);
		BigDecimal result = BigDecimal.ZERO;

		for (int i = 0; i <= LASTITER; i++) {
			BigDecimal factorial = factorial(new BigDecimal(i));
			BigDecimal res = BigDecimal.ONE.divide(factorial, mc);
			result = result.add(res);
		}
		printInfo("ended");
		return result;
	}

	private BigDecimal factorial(BigDecimal n) {
		if (n.equals(BigDecimal.ZERO)) {
			return BigDecimal.ONE;
		}

		return n.multiply(factorial(n.subtract(BigDecimal.ONE)));
	}

	private void printInfo(String message) {
		System.out.printf("%s with priority %s has %s.\n", Thread.currentThread().getName(),
				Thread.currentThread().getPriority(), message);
	}
}