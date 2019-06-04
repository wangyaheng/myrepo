package wyh;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ConfigurableApplicationContext;

import wyh.controller.DeptController;
import wyh.utils.ApplicationContextUtil;
//@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@SpringBootApplication
@EnableCircuitBreaker//对hystrixR熔断机制的支持
public class DeptProvider8001_App
{
  public static void main(String[] args)
  {
   ConfigurableApplicationContext run = SpringApplication.run(DeptProvider8001_App.class, args);
   DeptController bean = run.getBean(DeptController.class);
   System.out.println("================"+bean);
   ApplicationContextUtil.setApplicationContext(run);
  }
}
 

