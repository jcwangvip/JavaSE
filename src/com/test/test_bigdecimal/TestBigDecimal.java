package com.test.test_bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBigDecimal {

	public static void main(String[] args) {
		
		BigDecimal b = new BigDecimal("12.6666");
		BigDecimal c = new BigDecimal("13.3333");
		
		//System.out.println(b.divide(c,3).abs());
		
		System.out.println(b.subtract(c).setScale(3, RoundingMode.HALF_UP).abs());
	}
}
