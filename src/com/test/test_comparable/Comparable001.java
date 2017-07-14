package com.test.test_comparable;

public class Comparable001<T> implements Comparable<T> {

	@Override
	public int compareTo(T t) {
		
		Object o = t.getClass();
		
		if(!(o instanceof Comparable001)) return -1;
		
		return 0;
	}

}
