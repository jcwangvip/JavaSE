package com.test.test_comparable;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class TestComparable001 {

	@Test
	public void test() {
		BigDecimal b1 = new BigDecimal(111);
		BigDecimal b2 = new BigDecimal(222);
		
		int number = b1.compareTo(b2);
		System.out.println(number);
		
//		fail("Not yet implemented");
	}
	
	@Test
	public void testStringCompareTo(){
		String newString = "张三";
		String OldString = "李四";
		System.out.println(newString.compareTo(OldString));
	}
	
	
	@Test
	public void testCollections_sort(){
		
		String f1 ="a";
		String f2 ="b";
		String f3 ="c";
		String f4 ="d";

        List list = new ArrayList();
        list.add(f1);
        list.add(f3);
        list.add(f4);
        list.add(f2);
        Collections.sort(list);

        System.out.println(list);
	}

	@Test
	public void testTreeSet(){
		
		Set set=new TreeSet<String>();    
       //插入的时候，已经去重和排序，所以很方便 
	   //允许添加空
       set.add("13855555555");    
       set.add("13455555555");    
       set.add("13845555555");    
       set.add("13455555555");    
       set.add("kk1");    
       set.add("");    
       System.out.println(set.size());    
       Iterator it=set.iterator();    
       while(it.hasNext()){    
           System.out.println(it.next());    
       }         
       
	}

}
