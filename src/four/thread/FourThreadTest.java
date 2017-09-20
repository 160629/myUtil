package four.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FourThreadTest{  

	 /* POOL_NUM */  
    private static int POOL_NUM = 10;  
      
    /** 
     * Main function 
     */  
    public static void main(String[] args)  
    {  
        ExecutorService executorService = Executors.newFixedThreadPool(5);  
        for(int i = 0; i<POOL_NUM; i++)  
        {  
            RunnableThread thread = new RunnableThread();  
            executorService.execute(thread);  
        }  
    }  
}  
  
class RunnableThread implements Runnable  
{  
    private int THREAD_NUM = 10;  
    public void run()  
    {  
        for(int i = 0; i<THREAD_NUM; i++)  
        {  
            System.out.println("线程" + Thread.currentThread() + " " + i);  
        }   
    }  
}  