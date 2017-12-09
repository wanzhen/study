package com.glz.study.Study;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
 

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	public static void main(String[] args) {

		int index = 0;

//		for (int i = 0; i < index; i++) {
//			System.out.println(i);
//		}
		
		
	/*	AtomicInteger atomicInteger = new AtomicInteger(0);
		
		for (int i = 0; i < 100; i++) {
			System.out.println(atomicInteger.incrementAndGet());
		}
	    int max=20;
        int min=10;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println(s);
        */
		Random rand = new Random();
        StringBuilder sb = new StringBuilder(1000000);
        SimpleDateFormat  sf = new SimpleDateFormat ("yyyyMMddHHmmssSSS");
        AtomicInteger ato = new AtomicInteger(0);
        Map map = new LinkedHashMap();
        for (int i = 0; i < 100000; i++) {
		/*	String uuid =   UUID.randomUUID().toString();
			sb.append(uuid+",");
			uuid = uuid.substring(32);*/
        	;
        	int a = ato.incrementAndGet();
        	if(a > 9999){
        		 ato = new AtomicInteger(0);
        		 a = ato.incrementAndGet();
        	}
            String	str = String.valueOf(a);
			str = sf.format(System.currentTimeMillis()) + "" +str;
		 	sb.append(str + ",");
			if (map.get(str) != null) {
			   System.out.println(str);
			}
 			map.put(str,str);
        }
         System.out.println(sb.toString());
         
       

		 
		
	}

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}

	public Key floor(Key key) {

		Node x = floor(root, key);

		if (x == null) {

			return null;
		}

		return x.key;
	}

	public Node floor(Node x, Key key) {

		if (x == null) {
			return null;
		}

		int cmp = key.compareTo(x.key);

		if (cmp == 0) {
			return x;
		}
		if (cmp < 0) {
			x = floor(x.left, key);
		}

		Node f = floor(x.right, key);
		if (f == null)
			return x;
		else {
			return f;
		}
	}

	public Node put(Node x, Key key, Value val) {

		if (x == null) {
			return new Node(key, val);
		}

		int cmp = key.compareTo(x.key);

		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}

		return x;
	}

	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);

			if (cmp < 0) {
				x = x.left;
			} else if (cmp > 0) {

				x = x.right;
			} else {
				return x.val;
			}
		}

		return null;
	}

	public void delete(Key key) {
          
	}

	public Iterable<Key> iterator() {

		return null;
	}
}
