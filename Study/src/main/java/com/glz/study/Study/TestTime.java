package com.glz.study.Study;

import java.util.concurrent.SynchronousQueue;

public class TestTime {

	public static void main(String[] args) {
		
		String str = Long.toBinaryString(1512108684932L);
	
		System.out.println(str);

		System.out.println(Long.toBinaryString(1512108684932L<<22));
		
		System.out.println(Long.toBinaryString(1512108684932L<<22).length());
		
		System.out.println(Long.toBinaryString(0L<<17));
		
		System.out.println(~(1<<63) );

		System.out.println(Integer.MAX_VALUE);
	    
		System.out.println(Long.toBinaryString(~(1<<63)));
	
		System.out.println(Long.toBinaryString(1<<63));
		System.out.println(1<<63);
		
	}

}
