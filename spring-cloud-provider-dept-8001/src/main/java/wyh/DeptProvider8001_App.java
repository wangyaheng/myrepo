package wyh;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@SpringBootApplication
@EnableAspectJAutoProxy
public class DeptProvider8001_App
{
  public static void main(String[] args)
  {
   SpringApplication.run(DeptProvider8001_App.class, args);
  }
}
 

