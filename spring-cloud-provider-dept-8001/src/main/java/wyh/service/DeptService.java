package wyh.service;

import java.util.List;

import wyh.study.Dept;

public interface DeptService {
	 public boolean add(Dept dept);
	  public Dept  get(Long id);
	  public List<Dept> list();

}
