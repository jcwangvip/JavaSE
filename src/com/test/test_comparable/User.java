package com.test.test_comparable;

import java.math.BigDecimal;

public class User implements Comparable<User> {

	private String name;
	private int age;
	private BigDecimal sal ;

	
	public BigDecimal getSal() {
		return sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User() {
	}

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(User user) {
		if (this.age > user.getAge()) {
			return 1;
		}
		if (this.age < user.getAge()) {
			return -1;
		}
		// 按name排序
		if (this.name.compareTo(user.getName()) > 0) {
			return 1;
		}
		if (this.name.compareTo(user.getName()) < 0) {
			return -1;
		}
		return 0;
	}

	
	public void bubbleSort() {
		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,25, 53, 51 };
		int temp = 0;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]+",");
	}
	
	public void bubbleSort1() {
		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,25, 53, 51 };
		int temp = 0;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[i] > a[i + 1]) {
					temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;
				}
			}
		}
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]+",");
	}
	
	
	public void bubbleSort(int a[]) {
		int temp = 0;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]+",");
	}

}
