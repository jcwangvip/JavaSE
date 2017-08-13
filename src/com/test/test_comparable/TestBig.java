package com.test.test_comparable;

import java.math.BigDecimal;

public class TestBig {
	
	public static void main(String[] args) {
		
		User user = new User();
		
//		user.setSal(new BigDecimal(""));
//		System.out.println(user.getSal());
		TestBig testBig = new TestBig();
		
		String res= testBig.chBigDecimal(user.getSal());
		System.out.println(res);
		
		if(user.getSal() == null){
			System.out.println("sf");
		}
		if(user.getSal() != null){
			System.out.println("sdfsdfsdf");
		}
		
		if(user.getSal() != null)
		{
			System.out.println("sdf");
		}
		System.out.println("222222222222222222222222222222222222");
		if(("").equals(user.getSal()))
		{
			System.out.println("sdf");
		}
		
	}
	
	public String chBigDecimal(BigDecimal bigDecimal){
		String res = (bigDecimal == null) ? "null" : bigDecimal.toString();
		return res;
	}
}
