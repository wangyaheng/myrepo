package wyh.hystrix;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import rx.Observable;

public class TestHystrix {

	@SuppressWarnings("null")
	@Test
	public void test() throws InterruptedException{
		 int count =15;
		
		     while (count>0){
		          int id = count--;
		          new Thread(() -> {
		              try {
		            	  System.out.println(Thread.currentThread().getName()+"start");
		            	  //Observable<String> observe = new HystrixHelloWorld("name").observe();
		            	  HystrixHelloWorld hystrixHelloWorld = new HystrixHelloWorld("name");
		                  
		                  String execute =hystrixHelloWorld.execute();
		                  boolean circuitBreakerOpen = hystrixHelloWorld.isCircuitBreakerOpen();
		                 // Thread.sleep(3000);
		                  System.out.println(Thread.currentThread().getName()+execute+" "+ circuitBreakerOpen);
		              }catch (Exception ex){
		                  System.out.println("Exception:"+ex.getMessage()+" id="+id);
		              }
		          }).start();
		      }
		 
	      TimeUnit.SECONDS.sleep(1000000);
	}
}
