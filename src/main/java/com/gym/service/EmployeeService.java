package com.gym.service;

import com.gym.pojo.ClassTable;
import com.gym.pojo.Employee;
import com.gym.pojo.PageBean;

import java.util.List;

public interface EmployeeService {
    void add(Employee employee);

    

    Employee findById(Integer account);

    void update(Employee employee);

    void delete(Integer id);

    PageBean<Employee> list(Integer pageNum, Integer pageSize);

    List<ClassTable> classList(Integer id);
}
