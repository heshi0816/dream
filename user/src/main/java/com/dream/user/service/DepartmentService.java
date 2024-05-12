package com.dream.user.service;


import com.dream.user.entity.Department;

import java.util.List;

public interface DepartmentService {

	public List<Department> search();

	public Department searchById(Integer id);

	public boolean add(Department dep);

	public boolean update(Department dep);

	public boolean delete(Integer id);
}
