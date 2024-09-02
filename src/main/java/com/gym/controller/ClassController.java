package com.gym.controller;


import com.gym.pojo.*;
import com.gym.service.AdminService;
import com.gym.service.ClassService;
import com.gym.service.EmployeeService;
import com.gym.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/class")
@Validated
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public Result addClass(@RequestBody @Validated ClassTable classTable){

        Employee employee = employeeService.findById(classTable.getCoachId());
        if(employee == null){
            return Result.error("教练ID错误");
        }
        classService.addClass(classTable);
        return Result.success();

    }

    @GetMapping
    public Result<PageBean<ClassTable>> list(Integer pageNum, Integer pageSize){

        PageBean<ClassTable> pb =  classService.list(pageNum,pageSize);
        return Result.success(pb);

    }

    @GetMapping("/detail")
    public Result<ClassTable> detail(Integer id){
        ClassTable classTable = classService.findById(id);
        return Result.success(classTable);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated ClassTable classTable){
        Employee employee = employeeService.findById(classTable.getCoachId());
        if(employee == null){
            return Result.error("教练ID错误");
        }
        classTable.setCoachName(employee.getEmployeeName());
        classService.update(classTable);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){

        ClassTable classTable = classService.findById(id);
        if(LocalDateTime.now().isAfter(classTable.getClassBegin())){
            return Result.error("课程已经开始，无法删除");
        }
        List<ClassOrder> cs = classService.orderList(id);
        if(cs.isEmpty()){
            classService.delete(id);
            return Result.success();
        }
        for (ClassOrder order : cs) {
            // 在这里对每个ClassOrder对象进行操作
            System.out.println(order.getClassName()); // 示例：输出课程名称
            Member member = memberService.findByMemberAccount(order.getMemberAccount());
            member.setPoints(member.getPoints()+ classTable.getClassPoints());
            adminService.update(member);
        }
        classService.delete(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<ClassOrder>> list(Integer id){

        List<ClassOrder> cs = classService.orderList(id);
        return Result.success(cs);

    }

}
