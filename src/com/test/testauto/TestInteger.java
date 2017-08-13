package com.test.testauto;

public class TestInteger {

	public static void main(String[] args) {
		
		
		int a =1 ;
		Integer b = new Integer(1);
		Integer c = new Integer(1);

		
		System.out.println(a == b);
		System.out.println(c == b);
		
		System.out.println(b.equals(c));
	}
}
