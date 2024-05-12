package com.dream.user.service.impl;

import com.dream.user.dao.EmployeeDao;
import com.dream.user.entity.Employee;
import com.dream.user.service.EmployeeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
    EmployeeDao empDao;

	@Override
	public List<Employee> search(Employee condition) {
		List<Employee> list=null;

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getNumber() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("number").as(Integer.class), condition.getNumber());
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName() + "%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(condition.getGender())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("gender").as(String.class), condition.getGender());
                    predicates.add(predicate);
                }
                if (condition.getAge() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("age").as(Integer.class), condition.getAge());
                    predicates.add(predicate);
                }
                if (condition.getDep() != null&&condition.getDep().getId()!=null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("dep").get("id").as(Integer.class), condition.getDep().getId());
                    predicates.add(predicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        };
        list = empDao.findAll(specification);
        return list;
	}

	@Override
	public Employee searchById(Integer id) {
		Employee emp =empDao.findById(id).get();
		return emp;
	}

	@Override
	public boolean add(Employee emp) {
        Employee newEmp =empDao.save(emp);
		return newEmp!=null;
	}

	@Override
	public boolean update(Employee emp) {
        Employee newEmp =empDao.save(emp);
        return newEmp!=null;
	}

	@Override
	public boolean delete(Integer id) {
        try {
            empDao.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
	}
}
