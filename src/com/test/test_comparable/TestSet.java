package com.test.test_comparable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestSet {

	public static void main(String[] args) {


		Map map = new HashMap();
		map.put("1", "a");
//		map.put("2", "b");
//		map.put("3", "c");
		Set set = map.keySet();
		System.out.println(set);
		
		System.out.println(map.get(map.keySet()));
		
	}

}
