package wyh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import wyh.service.DeptService;
import wyh.study.Dept;
@Service
public class DeptServiceImpl implements DeptService{

	@Override
	public boolean add(Dept dept) {
		return false;
	}

	@Override
	public Dept get(Long id) {
		Dept d  = new Dept("aa");
		d.setDeptno(id);
		return d;
	}

	@Override
	public List<Dept> list() {
		List<Dept> list = new ArrayList<>();
		return list;
	}

}
