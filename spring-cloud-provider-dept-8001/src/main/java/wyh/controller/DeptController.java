package wyh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wyh.annotation.ExtRateLimit;
import wyh.service.DeptService;
import wyh.study.Dept;

@RestController
public class DeptController {
	@Autowired
	private DeptService deptService;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptService.get(id);
	}

	@ExtRateLimit(timeOut = 500l, timePerSecond = 1)
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		return deptService.list().toString();
	}

}
