package com.test.test_thread;

public class A {
	
	private int j;
	
	class B extends Thread{
		@Override
		public void run() {
			//super.run();
			j++;
			System.out.println(j);
		}
	}
	
	class C implements Runnable {
		@Override
		public void run() {
			j--;
			System.out.println(j);
		}
	}
	
	public static void main(String[] args) {
		
		A a = new A();
		B b = a.new B();
		//B b = new B();
		for(int i = 0 ; i<2;i++){
			Thread t = new Thread(b);  
			t.start();
		}
		
		
		C c = a.new C();
		for(int i = 0 ; i<2;i++){
			Thread ct = new Thread(c);  
			ct.start();
		}
		
		
	}
	
	
}
