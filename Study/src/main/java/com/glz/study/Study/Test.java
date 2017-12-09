package com.glz.study.Study;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

	private AtomicInteger ato = new AtomicInteger(0);

	public static void main(String[] args) {
		
		final Test test = new Test();

		for (int i = 0; i < 20; i++) {
			new Thread() {
				@Override
				public void run() {
					for (int i = 0; i < 100; i++) {
						int val = test.getId(1);
						System.out.println(val);
					}
				}
			}.start();
		}

		/*
		 * for (int i = 0; i < 100000; i++) { System.out.println(test.getId(1));
		 * }
		 */
	}

	public int getId(int step) {
		for (;;) {
			int curr = ato.get();
			int val = curr + step;
			if (ato.compareAndSet(curr, val)) {
				return val & 9;
			}
		}
	}
}
