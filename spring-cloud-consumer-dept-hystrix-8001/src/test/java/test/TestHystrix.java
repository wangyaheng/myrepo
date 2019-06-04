package test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import wyh.DeptProvider8001_App;
import wyh.service.DeptService;
import wyh.utils.ApplicationContextUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeptProvider8001_App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestHystrix {

	@Test
	public void test() throws InterruptedException{
		 int count =10;
		     while (count >0){
		          int id = count--;
		          new Thread(() -> {
		              try {
		                 // new HystrixHelloWorld("name"+count).execute();
		              }catch (Exception ex){
		                  System.out.println("Exception:"+ex.getMessage()+" id="+id);
		              }
		          }).start();
		      }
		   
		      TimeUnit.SECONDS.sleep(1000000);
	}
	
	@Test
	public void testBf(){
		ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
		DeptService bean = (DeptService)applicationContext.getBean("deptService");
		System.out.println(bean);
		
		
	}
}
