package com.dream.user.service.impl;

import com.dream.user.dao.DepartmentDao;
import com.dream.user.dao.EmployeeDao;
import com.dream.user.entity.Department;
import com.dream.user.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentDao depDao;
    @Autowired
    EmployeeDao empDao;
    @Override
    public List<Department> search() {
        List<Department> list = depDao.findAll();
        return list;
    }
    @Override
    public Department searchById(Integer id) {
        Department dep = depDao.findById(id).get();
        return dep;
    }
    @Override
    public boolean add(Department dep) {
        Department newDep = depDao.save(dep);
        return newDep != null;
    }
    @Override
    public boolean update(Department dep) {
        Department newDep = depDao.save(dep);
        return newDep != null;
    }
    @Override
    public boolean delete(Integer id) {
        try {
            empDao.updateByDep(id);
            depDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
