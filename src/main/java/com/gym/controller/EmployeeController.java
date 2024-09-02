package com.gym.controller;

import com.gym.pojo.*;
import com.gym.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Result add(@RequestBody @Validated Employee employee){
        employeeService.add(employee);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Employee>> list(Integer pageNum,Integer pageSize){

        PageBean<Employee> pb = employeeService.list(pageNum,pageSize);
        return Result.success(pb);

    }

    @GetMapping("/detail")
    public Result<Employee> detail(Integer account){

        Employee e = employeeService.findById(account);
        return Result.success(e);
    }

    @PutMapping
    public Result update(@RequestBody @Validated Employee employee){

        employeeService.update(employee);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        List<ClassTable> ct = employeeService.classList(id);
        if(ct.isEmpty()){
            employeeService.delete(id);
            return Result.success();
        }
        return Result.error("该教练有相关课程发布，请先处理");
    }

    @GetMapping("/list")
    public Result<List<ClassTable>> list(Integer id){

        List<ClassTable> ct = employeeService.classList(id);
        return Result.success(ct);

    }

}
