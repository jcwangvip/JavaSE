package com.test.testauto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Test {

	public static void main(String[] args) {

		
		short s1 =1;
		
		s1=(short) (s1+ 1);
		
		Map map =new HashMap();
		map.put("1", "1");
		map.put("1", "2");
		
	
		System.out.println(map.get("1"));
		
		for(int i = 0 ; i<10;i++){
			System.out.println("a"+i);
			for(int j = 0 ;j <10;j++){
				if(j == 6){
					System.out.println("b" + j);
//				return;
//				break;
//				continue;
				}
			}
		}
		
		
		boolean a = true;
		boolean b = false;
		
		if(aa() & bb()){
			System.out.println("---");
		}
		
//		if(bb() && aa()){
//			System.out.println("+++++++");
//		}
	}
	static boolean aa(){
		System.out.println("1111111111111");
		return false;}
	static boolean bb(){
		System.out.println("2222222222222222");
		return true;
		}
	

}
