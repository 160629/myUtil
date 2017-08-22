package arithmetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest implements Runnable{

	public ThreadTest() {
		// TODO Auto-generated constructor stub
	}
	
	private static int first = 5;
	private static int second = 20;
	private static int thirdly = 75;
	private static int a = 0;
	
	@Override
	public void run() {
/*		
		for (int i = 0; i < 10; i++) {
			System.out.println(a+++"...."+Thread.currentThread().getName());
		}*/
		win();

		

	}
	public  void win(){
		Random r = new Random();
		Lock  lock = new ReentrantLock();
	
			
		
		for (int i = 0; first>0 || second>0 || thirdly>0; i++) {
			
			int num = r.nextInt(100);
			lock.lock();
			if(num<5 && first>0){
				System.out.println(num+"......"+a+++"......first"+first+"一等獎"+"......"+Thread.currentThread().getName());
				first--;
			}
			if(num>=5 && num<25 && second>0){
				System.out.println(num+"......"+a+++"......second"+second+"二等獎"+"......"+Thread.currentThread().getName());
				second--;
			}
			if(num>=25 && thirdly>0){
				System.out.println(num+"......"+a+++"......thirdly"+thirdly+"三等獎"+"......"+Thread.currentThread().getName());
				thirdly--;
			}
			
			lock.unlock();
		}
		
	}
	public static void main(String[] args) {
		Thread th1 = new Thread(new ThreadTest());
		Thread th2 = new Thread(new ThreadTest());
		th1.start();
		th2.start();
	}

}
