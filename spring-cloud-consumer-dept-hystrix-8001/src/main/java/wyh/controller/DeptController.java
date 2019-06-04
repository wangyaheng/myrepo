package wyh.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wyh.service.DeptService;
import wyh.study.Dept;
import wyh.utils.ApplicationContextUtil;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@RestController 
@Controller
@RequestMapping
public class DeptController
{
  @Autowired
  private DeptService deptService;
  
  @RequestMapping(value="/dept/add",method=RequestMethod.POST)
  public boolean add(@RequestBody Dept dept)
  {
	 System.out.println(ApplicationContextUtil.getApplicationContext());
   return deptService.add(dept);
  }
  @HystrixCommand(fallbackMethod="processGet")
  @RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
  public Dept get(@PathVariable("id") Long id)
  {
   return deptService.get(id);
  }
  public Dept processGet() {
	  Dept dept = new Dept();
	  dept.setDname("hystrix 处理的方法");
	  return dept;
  }
  
  @RequestMapping(value="/dept/list",method=RequestMethod.GET)
  public List<Dept> list()
  { System.out.println(ApplicationContextUtil.getApplicationContext());
   return deptService.list();
  }
  
  
  
}
 

