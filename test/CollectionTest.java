package test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class CollectionTest {
	
	public static void main(String[] args) throws InterruptedException {
		final Table ta = new Table();
		final Table2 ta2 = new Table2();
		
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					try {
						ta.sleepMain();
	 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					ta2.sleepSub();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}								
			}
		}).start();

	
	}
		
}

class Table {
	
//	boolean flag = false;
	
	public synchronized void sleepMain() throws InterruptedException {
//		this.notify();
//		if(flag) {
//			// 互斥
//			this.wait();
//		}
		
		for (int i = 1; i <= 11; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
			if(i == 11) {
//				flag = true;
				this.notify();
				this.wait();
			}
		}
		
		
	}
	
	public synchronized void sleepSub() throws InterruptedException {
		
		
		// 在main 走完往前进入循环输出前都是 沉睡状态
//		if(!flag) {
//			this.wait();
//			this.notify();
//		}
		
		for (int i = 1; i <= 10; i++) {
			System.err.println(Thread.currentThread().getName() + ":" + i);
//			
			if(i == 10) {
//				flag = false;
				this.notify();
				this.wait();
			}
		}
	}

 }
		

class Table2 {
	public synchronized void sleepSub() throws InterruptedException {
		
		
		// 在main 走完往前进入循环输出前都是 沉睡状态
//		if(!flag) {
//			this.wait();
//			this.notify();
//		}
		
		for (int i = 1; i <= 10; i++) {
			System.err.println(Thread.currentThread().getName() + ":" + i);
//			
			if(i == 10) {
//				flag = false;
				this.notify();
				this.wait();
			}
		}
	}
}
