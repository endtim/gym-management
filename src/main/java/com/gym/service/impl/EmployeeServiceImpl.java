package com.gym.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gym.mapper.EmployeeMapper;
import com.gym.pojo.ClassTable;
import com.gym.pojo.Employee;
import com.gym.pojo.PageBean;
import com.gym.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void add(Employee employee) {

        Random random = new Random();
        String account1 = "1010";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);
        employee.setEmployeeAccount(account);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);
        employee.setEntryTime(nowDay);
        employeeMapper.add(employee);
    }



    @Override
    public Employee findById(Integer account) {
        return employeeMapper.findById(account);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    @Override
    public void delete(Integer id) {
        employeeMapper.delete(id);
    }

    @Override
    public PageBean<Employee> list(Integer pageNum, Integer pageSize) {
        PageBean<Employee> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Employee> as = employeeMapper.list();
        Page<Employee> p = (Page<Employee>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public List<ClassTable> classList(Integer id) {
        return employeeMapper.classList(id);
    }
}
