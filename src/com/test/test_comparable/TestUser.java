package com.test.test_comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUser {

	public static void main(String[] args) {

		User f1 = new User("tony", 11);
        User f2 = new User("jack", 11);
        User f3 = new User("tom", 12);
        User f4 = new User("jason", 44);
        
        System.out.println(f1.compareTo(f2));
        System.out.println("---------------------------");
        
        List<User> list = new ArrayList<User>();
        list.add(f1);
        list.add(f3);
        list.add(f4);
        list.add(f2);
        Collections.sort(list);

        for (User o : list) {
        	System.out.println(o.getName() + "-->" + o.getAge());
        }
        
        
	        
	}

}
