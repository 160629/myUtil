package four.thread;

public class TwoThreadTest implements Runnable{

	   private int i;    
	    public void run()    
	    {    
	        for(i = 0;i <100;i++)    
	        {    
	            System.out.println(Thread.currentThread().getName()+" "+i);    
	        }    
	    }    
	    public static void main(String[] args)    
	    {    
	        for(int i = 0;i < 100;i++)    
	        {    
	            System.out.println(Thread.currentThread().getName()+" "+i);    
	            if(i==20)    
	            {    
	            	TwoThreadTest rtt = new TwoThreadTest();    
	                new Thread(rtt,"新线程1").start();    
	                new Thread(rtt,"新线程2").start();    
	            }    
	        }    
	    
	    }   
	       
}
